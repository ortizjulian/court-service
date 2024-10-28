package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.RestaurantRequestDto;
import com.restaurant.court_service.application.mapper.RestaurantRequestDtoMapper;
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
    private final RestaurantRequestDtoMapper restaurantRequestDtoMapper;
    @Override
    public void createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRequestDtoMapper.restaurantRequestDtoToRestaurant(restaurantRequestDto);
        restaurantServicePort.createRestaurant(restaurant);
    }
}
