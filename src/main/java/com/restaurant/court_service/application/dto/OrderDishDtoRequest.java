package com.restaurant.court_service.application.dto;

import com.restaurant.court_service.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderDishDtoRequest {
    @NotNull(message = Constants.EXCEPTION_DISH_ID_MANDATORY)
    private Long id;

    @NotNull(message = Constants.EXCEPTION_DISH_QUANTITY_MANDATORY)
    @Positive(message = Constants.EXCEPTION_DISH_QUANTITY_MIN)
    private Integer quantity;

    public OrderDishDtoRequest(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public @NotNull(message = Constants.EXCEPTION_DISH_ID_MANDATORY) Long getId() {
        return id;
    }

    public void setId(@NotNull(message = Constants.EXCEPTION_DISH_ID_MANDATORY) Long id) {
        this.id = id;
    }

    public @NotNull(message = Constants.EXCEPTION_DISH_QUANTITY_MANDATORY) @Positive(message = Constants.EXCEPTION_DISH_QUANTITY_MIN) Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = Constants.EXCEPTION_DISH_QUANTITY_MANDATORY) @Positive(message = Constants.EXCEPTION_DISH_QUANTITY_MIN) Integer quantity) {
        this.quantity = quantity;
    }
}
