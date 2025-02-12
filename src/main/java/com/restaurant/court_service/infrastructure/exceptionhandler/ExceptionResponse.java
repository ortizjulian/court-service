package com.restaurant.court_service.infrastructure.exceptionhandler;


import com.restaurant.court_service.utils.Constants;

public enum ExceptionResponse {
    UPDATE_DISH_EXCEPTION(Constants.EXCEPTION_DISH_UPDATE_MANDATORY_FIELDS), ORDER_CANT_BE_ASSIGNED(Constants.EXCEPTION_ORDER_CANT_BE_ASSIGNED), ORDER_IS_ALREADY_IN_PREPARATION(Constants.EXCEPTION_ORDER_IS_ALREADY_IN_PREPARATION);
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}