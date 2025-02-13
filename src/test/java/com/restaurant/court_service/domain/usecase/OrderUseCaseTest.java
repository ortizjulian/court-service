package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.exception.*;
import com.restaurant.court_service.domain.model.*;
import com.restaurant.court_service.domain.spi.*;
import com.restaurant.court_service.utils.Constants;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IMessagingPersistencePort messagingPersistencePort;

    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    PlaceOrder placeOrder = new PlaceOrder(
            1L,
            Arrays.asList(new OrderDish(1L, 2), new OrderDish(2L, 1)),
            Constants.PENDING,
            1L
    );

    @Test
    void placeOrder_WhenClientAlreadyHasOrder_ShouldThrowClientAlreadyHasOrderException() {
        when(orderPersistencePort.clientHasAlreadyAnOrder(placeOrder.getClientId())).thenReturn(true);

        assertThrows(ClientAlreadyHasOrderException.class, () -> orderUseCase.placeOrder(placeOrder));

        verify(orderPersistencePort, never()).createOrder(any(PlaceOrder.class));
    }

    @Test
    void placeOrder_WhenRestaurantNotFound_ShouldThrowRestaurantNotFoundException() {
        when(orderPersistencePort.clientHasAlreadyAnOrder(placeOrder.getClientId())).thenReturn(false);
        when(restaurantPersistencePort.existById(placeOrder.getRestaurantId())).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> orderUseCase.placeOrder(placeOrder));

        verify(orderPersistencePort, never()).createOrder(any(PlaceOrder.class));
    }

    @Test
    void placeOrder_WhenDishNotFoundInRestaurant_ShouldThrowDishNotFoundException() {
        when(orderPersistencePort.clientHasAlreadyAnOrder(placeOrder.getClientId())).thenReturn(false);
        when(restaurantPersistencePort.existById(placeOrder.getRestaurantId())).thenReturn(true);

        Dish availableDish = new Dish(3L, "Pollo", 10000, "Polloo", "", null, null, true);
        when(dishPersistencePort.findDishesByRestaurantId(placeOrder.getRestaurantId())).thenReturn(List.of(availableDish));

        assertThrows(DishNotFoundException.class, () -> orderUseCase.placeOrder(placeOrder));

        verify(orderPersistencePort, never()).createOrder(any(PlaceOrder.class));
    }

    @Test
    void placeOrder_WhenValidOrder_ShouldCallCreateOrderAndCreateTraceability() {
        when(orderPersistencePort.clientHasAlreadyAnOrder(placeOrder.getClientId())).thenReturn(false);
        when(restaurantPersistencePort.existById(placeOrder.getRestaurantId())).thenReturn(true);

        Dish dish1 = new Dish(1L, "Pizza", 20000, "pizza", "", null, null, true);
        Dish dish2 = new Dish(2L, "Jugo", 10000, "jugo", "", null, null, true);
        when(dishPersistencePort.findDishesByRestaurantId(placeOrder.getRestaurantId())).thenReturn(List.of(dish1, dish2));

        Order createdOrder = new Order(1L, "Pending", LocalDate.now(), null);
        when(orderPersistencePort.createOrder(placeOrder)).thenReturn(createdOrder);

        String userEmail = "cliente@example.com";
        when(authenticationPersistencePort.getAuthenticatedUserEMail()).thenReturn(userEmail);

        orderUseCase.placeOrder(placeOrder);

        verify(orderPersistencePort, times(1)).createOrder(placeOrder);

        verify(authenticationPersistencePort, times(1)).getAuthenticatedUserEMail();

        verify(traceabilityPersistencePort, times(1)).createTraceability(any());
    }


    @Test
    void getAllOrders_WhenClientDoesNotBelongToRestaurant_ShouldThrowEntityNotFoundException() {
        Long clientId = 1L;
        String orderStatus = Constants.PENDING;
        when(restaurantPersistencePort.employeeRestaurant(clientId)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            orderUseCase.getAllOrders(0, 10, orderStatus, clientId);
        });
    }

    @Test
    void getAllOrders_WhenOrderStatusIsInvalid_ShouldThrowInvalidOrderStatusException() {
        Long clientId = 1L;
        String invalidOrderStatus = "INVALID_STATUS";
        when(restaurantPersistencePort.employeeRestaurant(clientId)).thenReturn(1L);

        assertThrows(InvalidOrderStatusException.class, () -> {
            orderUseCase.getAllOrders(0, 10, invalidOrderStatus, clientId);
        });
    }

    @Test
    void getAllOrders_WhenValidClientAndOrderStatus_ShouldReturnPageOfOrders() {
        Long clientId = 1L;
        String orderStatus = Constants.PENDING;
        Long restaurantId = 1L;
        PageCustom<Order> expectedPage = new PageCustom<>();

        when(restaurantPersistencePort.employeeRestaurant(clientId)).thenReturn(restaurantId);
        when(orderPersistencePort.getAllOrders(0, 10, orderStatus, restaurantId)).thenReturn(expectedPage);

        PageCustom<Order> result = orderUseCase.getAllOrders(0, 10, orderStatus, clientId);

        assertNotNull(result);
        assertEquals(expectedPage, result);
    }


    @Test
    void assignOrder_WhenOrderDoesNotExist_ShouldThrowEntityNotFoundException() {
        when(orderPersistencePort.existById(any(Long.class))).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> orderUseCase.assignOrder(1L, 1L));
    }


    @Test
    void assignOrder_WhenOrderIsNotPending_ShouldThrowOrderCantBeAssigned(){
        when(orderPersistencePort.existById(any(Long.class))).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(any(Long.class),eq(Constants.PENDING))).thenReturn(false);

        assertThrows(OrderCantBeAssigned.class, ()->orderUseCase.assignOrder(1L,1L));
    }

    @Test
    void assignOrder_WhenOrderIsPending_ShouldCallAssignOrderOnPersistencePort(){
        when(orderPersistencePort.existById(any(Long.class))).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(any(Long.class),eq(Constants.PENDING))).thenReturn(true);
        orderUseCase.assignOrder(1L,1L);

        verify(orderPersistencePort, times(1)).assignOrder(1L,1L);

    }

    @Test
    void finishOrder_WhenOrderNotFound_ShouldThrowEntityNotFoundException() {
        Long orderId = 1L;
        when(orderPersistencePort.existById(orderId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            orderUseCase.finishOrder(orderId);
        });
    }

    @Test
    void finishOrder_WhenOrderStatusIsNotInPreparation_ShouldThrowOrderCantBeAssigned() {

        Long orderId = 1L;
        when(orderPersistencePort.existById(orderId)).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(orderId, Constants.IN_PREPARATION)).thenReturn(false);

        assertThrows(OrderCantBeAssigned.class, () -> {
            orderUseCase.finishOrder(orderId);
        });
    }

    @Test
    void finishOrder_WhenOrderCanBeFinished_ShouldFinishOrder() {
        Long orderId = 1L;
        Long userId = 2L;
        String phone = "123456789";

        when(orderPersistencePort.existById(orderId)).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(orderId, Constants.IN_PREPARATION)).thenReturn(true);
        when(orderPersistencePort.getUserIdByOrderId(orderId)).thenReturn(userId);
        when(userPersistencePort.getUserPhoneNumber(userId)).thenReturn(phone);

        orderUseCase.finishOrder(orderId);

        verify(messagingPersistencePort).notifyClient(phone, orderId);
        verify(orderPersistencePort).finishOrder(orderId);
    }

    @Test
    void deliverOrder_WhenOrderNotFound_ShouldThrowEntityNotFoundException() {
        Long orderId = 1L;
        String code= "2020";
        when(orderPersistencePort.existById(orderId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            orderUseCase.deliverOrder(orderId,code);
        });
    }

    @Test
    void deliverOrder_WhenOrderStatusIsNotInReady_ShouldThrowOrderCantBeAssigned() {

        Long orderId = 1L;
        String code= "2020";
        when(orderPersistencePort.existById(orderId)).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(orderId, Constants.READY)).thenReturn(false);

        assertThrows(OrderCantBeAssigned.class, () -> {
            orderUseCase.deliverOrder(orderId,code);
        });
    }

    @Test
    void deliverOrder_WhenOrderIsReady_ShouldDeliverOrder() {
        Long orderId = 1L;
        String code= "2020";

        when(orderPersistencePort.existById(orderId)).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(orderId, Constants.READY)).thenReturn(true);


        orderUseCase.deliverOrder(orderId,code);

        verify(messagingPersistencePort).checkCode(orderId, code);
        verify(orderPersistencePort).deliverOrder(orderId);
    }

    @Test
    void cancelOrder_WhenOrderNotFound_ShouldThrowEntityNotFoundException() {
        Long orderId = 1L;
        Long clientId= 1L;
        when(orderPersistencePort.existById(orderId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            orderUseCase.cancelOrder(orderId,clientId);
        });
    }

    @Test
    void cancelOrder_WhenUserIsNotAuthorized_ShouldThrowEntityNotFoundException() {
        Long orderId = 1L;
        Long clientId = 1L;

        when(orderPersistencePort.existById(orderId)).thenReturn(true);
        when(orderPersistencePort.existByIdAndClientId(orderId, clientId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            orderUseCase.cancelOrder(orderId, clientId);
        });
    }


    @Test
    void cancelOrder_WhenOrderStatusIsNotInPending_ShouldThrowOrderIsAlreadyInPreparation() {
        Long orderId = 1L;
        Long clientId= 1L;
        when(orderPersistencePort.existById(orderId)).thenReturn(true);
        when(orderPersistencePort.existByIdAndClientId(orderId,clientId)).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(orderId, Constants.PENDING)).thenReturn(false);

        assertThrows(OrderIsAlreadyInPreparationException.class, () -> {
            orderUseCase.cancelOrder(orderId,clientId);
        });
    }

    @Test
    void cancelOrder_WhenOrderIsPendingAndClientIsAuthorized_ShouldCancelOrder() {
        Long orderId = 1L;
        Long clientId= 1L;

        when(orderPersistencePort.existById(orderId)).thenReturn(true);
        when(orderPersistencePort.existByIdAndClientId(orderId,clientId)).thenReturn(true);
        when(orderPersistencePort.checkOrderStatus(orderId, Constants.PENDING)).thenReturn(true);

        orderUseCase.cancelOrder(orderId,clientId);

        verify(orderPersistencePort).cancelOrder(orderId);
    }

}
