package com.restaurant.court_service.domain.model;

import java.util.List;

public class PlaceOrder {
    private Long restaurantId;
    private List<OrderDish> dishes;
    private String status;
    private Long clientId;

    public PlaceOrder(Long restaurantId, List<OrderDish> dishes, String status, Long clientId) {
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.status = status;
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderDish> dishes) {
        this.dishes = dishes;
    }
}
