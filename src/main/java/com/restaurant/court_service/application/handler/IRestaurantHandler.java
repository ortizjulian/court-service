package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.RestaurantRequestDto;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantRequestDto restaurantRequestDto);
}
