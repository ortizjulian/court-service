package com.restaurant.court_service.infrastructure.output.feign.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}