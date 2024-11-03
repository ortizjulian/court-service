package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.exception.ClientAlreadyHasOrderException;
import com.restaurant.court_service.domain.exception.DishNotFoundException;
import com.restaurant.court_service.domain.exception.RestaurantNotFoundException;
import com.restaurant.court_service.domain.model.*;
import com.restaurant.court_service.domain.spi.IDishPersistencePort;
import com.restaurant.court_service.domain.spi.IOrderPersistencePort;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    PlaceOrder placeOrder = new PlaceOrder(
            1L,
            Arrays.asList(new OrderDish(1L, 2), new OrderDish(2L, 1)),
            "Pending",
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
    void placeOrder_WhenValidOrder_ShouldCallCreateOrderOnPersistencePort() {
        when(orderPersistencePort.clientHasAlreadyAnOrder(placeOrder.getClientId())).thenReturn(false);
        when(restaurantPersistencePort.existById(placeOrder.getRestaurantId())).thenReturn(true);

        Dish dish1 = new Dish(1L, "Pizza", 20000, "pizza", "", null, null, true);
        Dish dish2 = new Dish(2L, "Jugo", 10000, "jugo", "", null, null, true);
        when(dishPersistencePort.findDishesByRestaurantId(placeOrder.getRestaurantId())).thenReturn(List.of(dish1, dish2));

        orderUseCase.placeOrder(placeOrder);

        verify(orderPersistencePort, times(1)).createOrder(placeOrder);
    }
}
