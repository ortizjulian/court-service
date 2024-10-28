package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.RestaurantDtoRequest;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantDtoRequest restaurantDtoRequest);
}
