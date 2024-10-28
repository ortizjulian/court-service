package com.restaurant.court_service.domain.exception;

public class RestaurantDuplicateNit extends RuntimeException {
    public RestaurantDuplicateNit(String message) {
        super(message);
    }
}
