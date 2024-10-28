package com.restaurant.court_service.infrastructure.output.jpa.mapper;

import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper {

    DishEntity toEntity(Dish dish);
}
