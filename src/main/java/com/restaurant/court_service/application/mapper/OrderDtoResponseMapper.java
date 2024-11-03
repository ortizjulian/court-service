package com.restaurant.court_service.application.mapper;

import com.restaurant.court_service.application.dto.OrderDtoResponse;
import com.restaurant.court_service.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderDtoResponseMapper {
    OrderDtoResponse toOrderDtoResponse(Order order);
}
