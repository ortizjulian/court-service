package com.restaurant.court_service.infrastructure.output.jpa.adapter;

import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.spi.IDishPersistencePort;
import com.restaurant.court_service.infrastructure.output.jpa.entity.CategoryEntity;
import com.restaurant.court_service.infrastructure.output.jpa.entity.DishEntity;
import com.restaurant.court_service.infrastructure.output.jpa.entity.RestaurantEntity;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.repository.ICategoryRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IDishRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IRestaurantRepository restaurantRepository;
    private final ICategoryRepository categoryRepository;
    private final DishEntityMapper dishEntityMapper;

    @Override
    public void createDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        Optional<RestaurantEntity> optionalRestaurantEntity= restaurantRepository.findById(dish.getRestaurant().getId());
        Optional<CategoryEntity> optionalCategoryEntity= categoryRepository.findById(dish.getCategory().getId());
        if (optionalRestaurantEntity.isPresent() && optionalCategoryEntity.isPresent()){
            dishEntity.setRestaurant(optionalRestaurantEntity.get());
            dishEntity.setCategory(optionalCategoryEntity.get());
            this.dishRepository.save(dishEntity);
        }
    }
}
