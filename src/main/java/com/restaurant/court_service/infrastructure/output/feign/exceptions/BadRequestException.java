package com.restaurant.court_service.infrastructure.output.feign.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}