package com.restaurant.court_service.application.mapper;

import com.restaurant.court_service.application.dto.RestaurantDtoRequest;
import com.restaurant.court_service.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestDtoMapper {
    Restaurant restaurantRequestDtoToRestaurant(RestaurantDtoRequest restaurantDtoRequest);
}
