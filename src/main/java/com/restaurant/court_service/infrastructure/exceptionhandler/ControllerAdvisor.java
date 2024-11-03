package com.restaurant.court_service.infrastructure.exceptionhandler;

import com.restaurant.court_service.domain.exception.*;
import com.restaurant.court_service.utils.Constants;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = Constants.RESPONSE_MESSAGE_KEY;

    @ExceptionHandler(ClientAlreadyHasOrderException.class)
    public ResponseEntity<Map<String, String>> handleClientAlreadyHasOrderException(
            ClientAlreadyHasOrderException clientAlreadyHasOrderException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, clientAlreadyHasOrderException.getMessage()));
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<Map<String, String>> handleInvalidOrderStatusException(
            InvalidOrderStatusException invalidOrderStatusException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, invalidOrderStatusException.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(
            EntityNotFoundException entityNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, entityNotFoundException.getMessage()));
    }

    @ExceptionHandler(RestaurantDuplicateNitException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantDuplicateNitException(
            RestaurantDuplicateNitException restaurantDuplicateNitException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, restaurantDuplicateNitException.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
            RestaurantNotFoundException restaurantNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, restaurantNotFoundException.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(
            CategoryNotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, categoryNotFoundException.getMessage()));
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDishNotFoundException(
            DishNotFoundException dishNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, dishNotFoundException.getMessage()));
    }
    @ExceptionHandler(UpdateDishException.class)
    public ResponseEntity<Map<String, String>> handleUpdateDishException(
            UpdateDishException updateDishException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.UPDATE_DISH_EXCEPTION.getMessage()));
    }

    @ExceptionHandler(PaginationParametersInvalidException.class)
    public ResponseEntity<Map<String, String>> handlePaginationParametersInvalidException(
            PaginationParametersInvalidException paginationParametersInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, paginationParametersInvalidException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
