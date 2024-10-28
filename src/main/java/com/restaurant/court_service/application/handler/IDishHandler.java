package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.DishDtoRequest;

public interface IDishHandler {
    void createDish(DishDtoRequest dishDtoRequest);
}
