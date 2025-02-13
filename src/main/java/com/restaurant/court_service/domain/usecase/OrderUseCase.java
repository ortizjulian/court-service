package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.api.IOrderServicePort;
import com.restaurant.court_service.domain.exception.*;
import com.restaurant.court_service.domain.model.*;
import com.restaurant.court_service.domain.spi.*;
import com.restaurant.court_service.utils.Constants;
import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderUseCase implements IOrderServicePort{

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IMessagingPersistencePort messagingPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final ITraceabilityPersistencePort traceabilityPersistencePort;

    public OrderUseCase(IRestaurantPersistencePort restaurantPersistencePort, IDishPersistencePort dishPersistencePort, IOrderPersistencePort orderPersistencePort, IMessagingPersistencePort messagingPersistencePort, IUserPersistencePort userPersistencePort, IAuthenticationPersistencePort authenticationPersistencePort, ITraceabilityPersistencePort traceabilityPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
        this.messagingPersistencePort = messagingPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.traceabilityPersistencePort = traceabilityPersistencePort;
    }

    @Override
    public void placeOrder(PlaceOrder placeOrder) {

        if (orderPersistencePort.clientHasAlreadyAnOrder(placeOrder.getClientId())) {
            throw new ClientAlreadyHasOrderException(Constants.EXCEPTION_CLIENT_ALREADY_HAS_ORDER);
        }

        if(!restaurantPersistencePort.existById(placeOrder.getRestaurantId())){
            throw new RestaurantNotFoundException(Constants.EXCEPTION_RESTAURANT_NOT_FOUND + placeOrder.getRestaurantId());
        }

        validateDishInRestaurant(placeOrder);
        placeOrder.setStatus(Constants.PENDING);

        Order order= orderPersistencePort.createOrder(placeOrder);
        String email = authenticationPersistencePort.getAuthenticatedUserEMail();
        Traceability traceability = new Traceability(order.getId(),placeOrder.getClientId().toString(), email);

        traceabilityPersistencePort.createTraceability(traceability);

    }

    @Override
    public PageCustom<Order> getAllOrders(Integer page, Integer size, String orderStatus, Long clientId) {

        Long restaurantId = restaurantPersistencePort.employeeRestaurant(clientId);

        if (restaurantId == null) {
            throw new EntityNotFoundException(Constants.EXCEPTION_EMPLOYEE_DOES_NOT_BELONG_TO_RESTAURANT);
        }

        if (!Constants.statuses.contains(orderStatus)) {
            throw new InvalidOrderStatusException(Constants.EXCEPTION_INVALID_ORDER_STATUS + orderStatus);
        }

        return this.orderPersistencePort.getAllOrders(page, size, orderStatus, restaurantId);
    }

    @Override
    public void assignOrder(Long employeeId, Long orderId) {

        if(!orderPersistencePort.existById(orderId)){
            throw new EntityNotFoundException(Constants.EXCEPTION_ORDER_NOT_FOUND);
        }

        if(!orderPersistencePort.checkOrderStatus(orderId, Constants.PENDING)){
            throw new OrderCantBeAssigned();
        }

        orderPersistencePort.assignOrder(employeeId,orderId);

        String email = authenticationPersistencePort.getAuthenticatedUserEMail();
        Traceability traceability = new Traceability(orderId,employeeId, email);

        traceabilityPersistencePort.createTraceability(traceability);
        StateUpdate stateUpdate = new StateUpdate(orderId,Constants.PENDING,Constants.IN_PREPARATION);
        traceabilityPersistencePort.updateOrderStatus(stateUpdate);

    }

    @Override
    public void finishOrder(Long orderId) {

        if(!orderPersistencePort.existById(orderId)){
            throw new EntityNotFoundException(Constants.EXCEPTION_ORDER_NOT_FOUND);
        }

        if(!orderPersistencePort.checkOrderStatus(orderId, Constants.IN_PREPARATION)){
            throw new OrderCantBeAssigned();
        }

        Long userId = orderPersistencePort.getUserIdByOrderId(orderId);
        String phone = userPersistencePort.getUserPhoneNumber(userId);

        messagingPersistencePort.notifyClient(phone, orderId);

        orderPersistencePort.finishOrder(orderId);

        StateUpdate stateUpdate = new StateUpdate(orderId,Constants.IN_PREPARATION,Constants.READY);
        traceabilityPersistencePort.updateOrderStatus(stateUpdate);
    }

    @Override
    public void deliverOrder(Long orderId, String code) {
        if(!orderPersistencePort.existById(orderId)){
            throw new EntityNotFoundException(Constants.EXCEPTION_ORDER_NOT_FOUND);
        }

        if(!orderPersistencePort.checkOrderStatus(orderId, Constants.READY)){
            throw new OrderCantBeAssigned();
        }

        messagingPersistencePort.checkCode(orderId,code);

        orderPersistencePort.deliverOrder(orderId);

        StateUpdate stateUpdate = new StateUpdate(orderId,Constants.READY,Constants.DELIVERED);
        traceabilityPersistencePort.updateOrderStatus(stateUpdate);
    }

    @Override
    public void cancelOrder(Long orderId, Long clientId) {
        if(!orderPersistencePort.existById(orderId)){
            throw new EntityNotFoundException(Constants.EXCEPTION_ORDER_NOT_FOUND);
        }

        if (!orderPersistencePort.existByIdAndClientId(orderId,clientId)){
            throw new EntityNotFoundException(Constants.NOT_AUTHORIZED_TO_ACCESS_ORDER);
        }

        if(!orderPersistencePort.checkOrderStatus(orderId, Constants.PENDING)){
            throw new OrderIsAlreadyInPreparationException();
        }

        orderPersistencePort.cancelOrder(orderId);
        StateUpdate stateUpdate = new StateUpdate(orderId,Constants.PENDING,Constants.CANCELED);
        traceabilityPersistencePort.updateOrderStatus(stateUpdate);

    }


    private void validateDishInRestaurant(PlaceOrder placeOrder) {

        List<Dish> availableDishes = dishPersistencePort.findDishesByRestaurantId(placeOrder.getRestaurantId());

        Set<Long> availableDishIds = availableDishes.stream()
                .map(Dish::getId)
                .collect(Collectors.toSet());

        for (OrderDish orderDish : placeOrder.getDishes()) {
            if (!availableDishIds.contains(orderDish.getId())) {
                throw new DishNotFoundException(Constants.EXCEPTION_DISH_NOT_FOUND_IN_RESTAURANT + orderDish.getId());
            }
        }
    }

}
