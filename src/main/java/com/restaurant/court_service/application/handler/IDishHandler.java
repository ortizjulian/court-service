package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.DishDtoRequest;
import com.restaurant.court_service.application.dto.DishDtoUpdate;

public interface IDishHandler {
    void createDish(DishDtoRequest dishDtoRequest);

    void updateDish(DishDtoUpdate dishDtoUpdate);
}
