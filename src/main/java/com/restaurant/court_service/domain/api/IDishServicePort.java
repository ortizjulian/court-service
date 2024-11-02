package com.restaurant.court_service.domain.api;

import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.DishUpdate;
import com.restaurant.court_service.domain.model.PageCustom;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(DishUpdate dishUpdate);
    void changeDishStatus(Long id, boolean status);

    PageCustom<Dish> getAllDishes(Integer page, Integer size, String sortDirection, String sortBy, String restaurantId, String categoryId);
}
