package com.restaurant.court_service.domain.exception;

public class ClientAlreadyHasOrderException extends RuntimeException {
    public ClientAlreadyHasOrderException(String message) {
        super(message);
    }
}