package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant);
    Boolean existById(Long id);
    Boolean existByNit(String nit);
    PageCustom<Restaurant> getAllRestaurants(Integer page, Integer size, String sortDirection, String sortBy);
}
