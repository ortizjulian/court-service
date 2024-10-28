package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.Dish;

public interface IDishPersistencePort {
    void createDish(Dish dish);
}
