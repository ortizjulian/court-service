package com.restaurant.court_service.domain.api;

import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);

    PageCustom<Restaurant> getAllRestaurants(Integer page, Integer size, String sortDirection, String sortBy);
}
