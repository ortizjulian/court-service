package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.RestaurantDtoRequest;
import com.restaurant.court_service.application.mapper.RestaurantDtoRequestMapper;
import com.restaurant.court_service.domain.api.IRestaurantServicePort;
import com.restaurant.court_service.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDtoRequestMapper restaurantRequestDtoMapper;
    @Override
    public void createRestaurant(RestaurantDtoRequest restaurantDtoRequest) {
        Restaurant restaurant = restaurantRequestDtoMapper.restaurantRequestDtoToRestaurant(restaurantDtoRequest);
        restaurantServicePort.createRestaurant(restaurant);
    }
}
