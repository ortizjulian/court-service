package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.DishDtoRequest;
import com.restaurant.court_service.application.mapper.DishDtoRequestMapper;
import com.restaurant.court_service.domain.api.IDishServicePort;
import com.restaurant.court_service.domain.model.Category;
import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final DishDtoRequestMapper dishDtoRequestMapper;

    @Override
    public void createDish(DishDtoRequest dishDtoRequest) {
        Dish dish = dishDtoRequestMapper.toDish(dishDtoRequest);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(dishDtoRequest.getRestaurantId());
        Category category = new Category();
        category.setId(dishDtoRequest.getCategoryId());

        dish.setRestaurant(restaurant);
        dish.setCategory(category);
        dishServicePort.createDish(dish);
    }
}
