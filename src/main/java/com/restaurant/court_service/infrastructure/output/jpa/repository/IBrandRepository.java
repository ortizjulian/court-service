package com.restaurant.court_service.infrastructure.output.jpa.repository;

import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity,Long> {
    Optional<BrandEntity> findByName(String brandName);
}
