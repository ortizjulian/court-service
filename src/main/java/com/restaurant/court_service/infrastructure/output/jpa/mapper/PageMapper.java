package com.restaurant.court_service.infrastructure.output.jpa.mapper;


import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RestaurantEntityMapper.class})
public interface PageMapper {

    RestaurantEntityMapper RESTAURANT_ENTITY_MAPPER = Mappers.getMapper(RestaurantEntityMapper.class);

    default PageCustom<Restaurant> toRestaurantPageCustom(Page<RestaurantEntity> page) {
        PageCustom<Restaurant> pageCustom = new PageCustom<>();
        List<Restaurant> restaurants = page.getContent().stream()
                .map(RESTAURANT_ENTITY_MAPPER::toRestaurant)
                .toList();

        pageCustom.setContent(restaurants);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.hasNext());
        pageCustom.setHasPrevious(page.hasPrevious());
        return pageCustom;
    }

}
