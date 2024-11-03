package com.restaurant.court_service.infrastructure.output.jpa.mapper;

import com.restaurant.court_service.domain.model.Order;
import com.restaurant.court_service.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {
    Order toOrder(OrderEntity orderEntity);
}
