package com.restaurant.court_service.infrastructure.output.jpa.repository;

import com.restaurant.court_service.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findByClientIdAndStatusIn(Long clientId, List<String> statuses);
}
