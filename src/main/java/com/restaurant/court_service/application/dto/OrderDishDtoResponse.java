package com.restaurant.court_service.application.dto;

public class OrderDishDtoResponse {
    private DishDtoResponse dish;
    private Integer quantity;

    public OrderDishDtoResponse(Integer quantity, DishDtoResponse dish) {
        this.quantity = quantity;
        this.dish = dish;
    }

    public DishDtoResponse getDish() {
        return dish;
    }

    public void setDish(DishDtoResponse dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
