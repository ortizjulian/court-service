package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant);
}
