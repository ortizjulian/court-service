package com.restaurant.court_service.domain.model;

public class OrderDish {
    private Long id;
    private Integer quantity;
    private Dish dish;

    public OrderDish(){}

    public OrderDish(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OrderDish(Dish dish, Integer quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
