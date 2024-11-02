package com.restaurant.court_service.domain.exception;

public class PaginationParametersInvalidException extends RuntimeException {
    public PaginationParametersInvalidException(String message) {
        super(message);
    }
}