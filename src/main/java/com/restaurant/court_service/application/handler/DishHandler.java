package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.DishDtoRequest;
import com.restaurant.court_service.application.dto.DishDtoResponse;
import com.restaurant.court_service.application.dto.DishDtoUpdate;
import com.restaurant.court_service.application.mapper.DishDtoRequestMapper;
import com.restaurant.court_service.application.mapper.DishDtoUpdateMapper;
import com.restaurant.court_service.application.mapper.PageDtoMapper;
import com.restaurant.court_service.domain.api.IDishServicePort;
import com.restaurant.court_service.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final DishDtoRequestMapper dishDtoRequestMapper;
    private final DishDtoUpdateMapper dishDtoUpdateMapper;
    private final PageDtoMapper pageDtoMapper;

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

    @Override
    public void updateDish(DishDtoUpdate dishDtoUpdate) {
        DishUpdate dishUpdate = dishDtoUpdateMapper.toDishUpdate(dishDtoUpdate);
        dishServicePort.updateDish(dishUpdate);
    }

    @Override
    public void changeDishStatus(Long id, boolean status) {
        dishServicePort.changeDishStatus(id,status);
    }

    @Override
    public PageCustom<DishDtoResponse> getAllDishes(Integer page, Integer size, String sortDirection, String sortBy, String restaurantId, String categoryId) {
        PageCustom<Dish> dishPage = this.dishServicePort.getAllDishes(page,size,sortDirection,sortBy,restaurantId,categoryId);
        return pageDtoMapper.toDishDtoPageCustom(dishPage);
    }
}
