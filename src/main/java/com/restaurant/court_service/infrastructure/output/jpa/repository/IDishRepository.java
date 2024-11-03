package com.restaurant.court_service.infrastructure.output.jpa.repository;

import com.restaurant.court_service.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IDishRepository extends JpaRepository<DishEntity,Long>,JpaSpecificationExecutor<DishEntity> {
    List<DishEntity> findByRestaurantId(Long restaurantId);
}
