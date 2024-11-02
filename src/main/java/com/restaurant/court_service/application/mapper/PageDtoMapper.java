package com.restaurant.court_service.application.mapper;

import com.restaurant.court_service.application.dto.DishDtoResponse;
import com.restaurant.court_service.application.dto.RestaurantDtoResponse;
import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RestaurantDtoResponseMapper.class,DishDtoResponseMapper.class })
public interface PageDtoMapper {

    RestaurantDtoResponseMapper RESTAURANT_DTO_RESPONSE_MAPPER = Mappers.getMapper(RestaurantDtoResponseMapper.class);
    DishDtoResponseMapper DISH_DTO_RESPONSE_MAPPER = Mappers.getMapper(DishDtoResponseMapper.class);

    default PageCustom<RestaurantDtoResponse> toRestaurantDtoPageCustom(PageCustom<Restaurant> page) {
        PageCustom<RestaurantDtoResponse> pageCustom = new PageCustom<>();
        List<RestaurantDtoResponse> restaurants = page.getContent().stream()
                .map(RESTAURANT_DTO_RESPONSE_MAPPER::toRestaurantDtoResponse)
                .toList();

        pageCustom.setContent(restaurants);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.getHasNext());
        pageCustom.setHasPrevious(page.getHasPrevious());
        return pageCustom;
    }

    default PageCustom<DishDtoResponse> toDishDtoPageCustom(PageCustom<Dish> page){
        PageCustom<DishDtoResponse> pageCustom = new PageCustom<>();
        List<DishDtoResponse> dishes = page.getContent().stream()
                .map(DISH_DTO_RESPONSE_MAPPER::toDishDtoResponse)
                .toList();

        pageCustom.setContent(dishes);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.getHasNext());
        pageCustom.setHasPrevious(page.getHasPrevious());
        return pageCustom;
    }

}
