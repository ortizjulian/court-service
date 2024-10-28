package com.restaurant.court_service.domain.api;

import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.DishUpdate;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(DishUpdate dishUpdate);
}
