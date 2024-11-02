package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.DishDtoRequest;
import com.restaurant.court_service.application.dto.DishDtoResponse;
import com.restaurant.court_service.application.dto.DishDtoUpdate;
import com.restaurant.court_service.domain.model.PageCustom;

public interface IDishHandler {
    void createDish(DishDtoRequest dishDtoRequest);

    void updateDish(DishDtoUpdate dishDtoUpdate);

    void changeDishStatus(Long id, boolean status);

    PageCustom<DishDtoResponse> getAllDishes(Integer page, Integer size, String sortDirection, String sortBy, String restaurantId, String categoryId);
}
