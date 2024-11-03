package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.DishUpdate;
import com.restaurant.court_service.domain.model.PageCustom;

import java.util.List;

public interface IDishPersistencePort {
    void createDish(Dish dish);
    void updateDish(DishUpdate dishUpdate);
    boolean existById(Long id);
    void changeDishStatus(Long id, boolean status);
    PageCustom<Dish> getAllDishes(Integer page, Integer size, String sortDirection, String sortBy, String restaurantId, String categoryId);
    List<Dish> findDishesByRestaurantId(Long restaurantId);
}
