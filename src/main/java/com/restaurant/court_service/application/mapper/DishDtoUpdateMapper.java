package com.restaurant.court_service.application.mapper;


import com.restaurant.court_service.application.dto.DishDtoUpdate;
import com.restaurant.court_service.domain.model.DishUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishDtoUpdateMapper {
    DishUpdate toDishUpdate(DishDtoUpdate dishDtoUpdate);
}
