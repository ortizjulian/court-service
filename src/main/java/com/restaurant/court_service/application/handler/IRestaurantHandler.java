package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.RestaurantDtoRequest;
import com.restaurant.court_service.application.dto.RestaurantDtoResponse;
import com.restaurant.court_service.domain.model.PageCustom;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantDtoRequest restaurantDtoRequest);

    PageCustom<RestaurantDtoResponse> getAllRestaurants(Integer page, Integer size, String sortDirection, String sortBy);
}
