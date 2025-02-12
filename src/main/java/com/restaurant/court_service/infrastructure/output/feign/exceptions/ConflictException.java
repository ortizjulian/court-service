package com.restaurant.court_service.infrastructure.output.feign.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
