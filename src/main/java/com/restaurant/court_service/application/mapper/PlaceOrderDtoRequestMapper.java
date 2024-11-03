package com.restaurant.court_service.application.mapper;

import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.domain.model.PlaceOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlaceOrderDtoRequestMapper {

    PlaceOrder toPlaceOrder(PlaceOrderDtoRequest placeOrderDtoRequest);
}
