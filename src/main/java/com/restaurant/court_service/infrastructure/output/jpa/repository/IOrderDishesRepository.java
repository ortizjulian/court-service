package com.restaurant.court_service.infrastructure.output.jpa.repository;

import com.restaurant.court_service.infrastructure.output.jpa.entity.OrderDishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishesRepository extends JpaRepository<OrderDishesEntity,Long> {
}
