package com.restaurant.court_service.infrastructure.output.jpa.repository;

import com.restaurant.court_service.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
    boolean existsByNit(String nit);

    @Query("SELECT r.id FROM RestaurantEntity r JOIN r.employeeIds e WHERE e = :clientId")
    Optional<Long> findRestaurantIdByEmployeeId(@Param("clientId") Long clientId);
}
