package com.restaurant.court_service.infrastructure.exceptionhandler;


import com.restaurant.court_service.utils.Constants;

public enum ExceptionResponse {
    UPDATE_DISH_EXCEPTION(Constants.EXCEPTION_DISH_UPDATE_MANDATORY_FIELDS);
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}