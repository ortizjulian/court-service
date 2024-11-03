package com.restaurant.court_service.application.dto;

import com.restaurant.court_service.utils.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PlaceOrderDtoRequest {
    @NotNull(message = Constants.EXCEPTION_ORDER_RESTAURANT_ID_MANDATORY)
    private Long restaurantId;

    @NotNull(message = Constants.EXCEPTION_ORDER_DISHES_EMPTY)
    @NotEmpty(message = Constants.EXCEPTION_ORDER_DISHES_EMPTY)
    private List<OrderDishDtoRequest> dishes;

    public PlaceOrderDtoRequest(Long restaurantId, List<OrderDishDtoRequest> dishes) {
        this.restaurantId = restaurantId;
        this.dishes = dishes;
    }

    public @NotNull(message = Constants.EXCEPTION_ORDER_RESTAURANT_ID_MANDATORY) Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(@NotNull(message = Constants.EXCEPTION_ORDER_RESTAURANT_ID_MANDATORY) Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public @NotNull(message = Constants.EXCEPTION_ORDER_DISHES_EMPTY) @NotEmpty(message = Constants.EXCEPTION_ORDER_DISHES_EMPTY) List<OrderDishDtoRequest> getDishes() {
        return dishes;
    }

    public void setDishes(@NotNull(message = Constants.EXCEPTION_ORDER_DISHES_EMPTY) @NotEmpty(message = Constants.EXCEPTION_ORDER_DISHES_EMPTY) List<OrderDishDtoRequest> dishes) {
        this.dishes = dishes;
    }
}
