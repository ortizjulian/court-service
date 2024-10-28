package com.restaurant.court_service.domain.exception;

public class RestaurantDuplicateNitException extends RuntimeException {
    public RestaurantDuplicateNitException(String message) {
        super(message);
    }
}
