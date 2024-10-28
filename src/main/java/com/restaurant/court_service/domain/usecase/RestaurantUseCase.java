package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.api.IRestaurantServicePort;
import com.restaurant.court_service.domain.exception.RestaurantDuplicateNit;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.utils.Constants;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {

        if(restaurantPersistencePort.existByNit(restaurant.getNit())){
            throw new RestaurantDuplicateNit(Constants.EXCEPTION_RESTAURANT_DUPLICATED_NIT +restaurant.getNit());
        }
        restaurantPersistencePort.createRestaurant(restaurant);
    }
}
