package com.restaurant.court_service.domain;

import com.restaurant.court_service.domain.api.IRestaurantServicePort;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.createRestaurant(restaurant);
    }
}
