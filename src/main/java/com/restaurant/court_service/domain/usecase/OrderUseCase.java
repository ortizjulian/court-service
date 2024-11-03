package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.api.IOrderServicePort;
import com.restaurant.court_service.domain.exception.ClientAlreadyHasOrderException;
import com.restaurant.court_service.domain.exception.DishNotFoundException;
import com.restaurant.court_service.domain.exception.RestaurantNotFoundException;
import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.OrderDish;
import com.restaurant.court_service.domain.model.PlaceOrder;
import com.restaurant.court_service.domain.spi.IDishPersistencePort;
import com.restaurant.court_service.domain.spi.IOrderPersistencePort;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.utils.Constants;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderUseCase implements IOrderServicePort{

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IRestaurantPersistencePort restaurantPersistencePort, IDishPersistencePort dishPersistencePort, IOrderPersistencePort orderPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
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
        orderPersistencePort.createOrder(placeOrder);
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
