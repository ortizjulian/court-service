package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.api.IRestaurantServicePort;
import com.restaurant.court_service.domain.exception.RestaurantDuplicateNitException;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.domain.utils.PaginationValidator;
import com.restaurant.court_service.utils.Constants;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {

        if(restaurantPersistencePort.existByNit(restaurant.getNit())){
            throw new RestaurantDuplicateNitException(Constants.EXCEPTION_RESTAURANT_DUPLICATED_NIT +restaurant.getNit());
        }
        restaurantPersistencePort.createRestaurant(restaurant);
    }

    @Override
    public PageCustom<Restaurant> getAllRestaurants(Integer page, Integer size, String sortDirection, String sortBy) {
        PaginationValidator.validatePagination(page,size,sortDirection);

        return this.restaurantPersistencePort.getAllRestaurants(page,size,sortDirection,sortBy);
    }
}
