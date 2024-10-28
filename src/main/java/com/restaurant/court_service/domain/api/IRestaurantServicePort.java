package com.restaurant.court_service.domain.api;

import com.restaurant.court_service.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);
}
