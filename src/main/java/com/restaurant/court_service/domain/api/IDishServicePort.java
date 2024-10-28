package com.restaurant.court_service.domain.api;

import com.restaurant.court_service.domain.model.Dish;

public interface IDishServicePort {
    void createDish(Dish dish);
}
