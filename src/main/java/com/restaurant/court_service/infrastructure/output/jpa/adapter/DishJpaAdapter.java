package com.restaurant.court_service.infrastructure.output.jpa.adapter;

import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.DishUpdate;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.spi.IDishPersistencePort;
import com.restaurant.court_service.infrastructure.output.jpa.entity.CategoryEntity;
import com.restaurant.court_service.infrastructure.output.jpa.entity.DishEntity;
import com.restaurant.court_service.infrastructure.output.jpa.entity.RestaurantEntity;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.PageMapper;
import com.restaurant.court_service.infrastructure.output.jpa.repository.ICategoryRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IDishRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.restaurant.court_service.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IRestaurantRepository restaurantRepository;
    private final ICategoryRepository categoryRepository;
    private final DishEntityMapper dishEntityMapper;
    private final PageMapper pageMapper;
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

    @Override
    public void updateDish(DishUpdate dishUpdate) {
        Long dishId = dishUpdate.getId();
        Optional<DishEntity> optionalDish = dishRepository.findById(dishId);

        if(optionalDish.isPresent()){
            DishEntity existingDish = optionalDish.get();
            if (dishUpdate.getPrice() != null) {
                existingDish.setPrice(dishUpdate.getPrice());
            }
            if (dishUpdate.getDescription() != null) {
                existingDish.setDescription(dishUpdate.getDescription());
            }
            dishRepository.save(existingDish);
        }

    }

    @Override
    public boolean existById(Long id) {
        return dishRepository.existsById(id);
    }

    @Override
    public void changeDishStatus(Long id, boolean status) {
        Optional<DishEntity> optionalDish = dishRepository.findById(id);

        if(optionalDish.isPresent()) {
            DishEntity existingDish = optionalDish.get();
            existingDish.setActive(status);
            dishRepository.save(existingDish);
        }
    }

    @Override
    public PageCustom<Dish> getAllDishes(Integer page, Integer size, String sortDirection, String sortBy, String restaurantId, String categoryId) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<DishEntity> spec = Specification.where(null);

        if (restaurantId != null && !restaurantId.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(Constants.RESTAURANT_ENTITY).get(Constants.DISH_ID), restaurantId));
        }

        if (categoryId != null && !categoryId.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.join(Constants.CATEGORY_ENTITY).get(Constants.DISH_ID), categoryId));
        }
        Page<DishEntity> dishPage = dishRepository.findAll(spec, pageable);

        return pageMapper.toDishPageCustom(dishPage);
    }
}
