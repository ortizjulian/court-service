package com.restaurant.court_service.infrastructure.output.jpa.repository;

import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByName(String categoryName);

}
