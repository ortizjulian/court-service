package com.restaurant.court_service.application.mapper;

import com.restaurant.court_service.application.dto.DishDtoRequest;
import com.restaurant.court_service.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishDtoRequestMapper {
    Dish toDish(DishDtoRequest dishDtoRequest);
}
