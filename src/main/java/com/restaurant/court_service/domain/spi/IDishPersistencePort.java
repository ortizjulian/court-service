package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.DishUpdate;

public interface IDishPersistencePort {
    void createDish(Dish dish);
    void updateDish(DishUpdate dishUpdate);
    boolean existById(Long id);
}
