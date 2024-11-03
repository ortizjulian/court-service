package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;

public interface IOrderHandler {
    void placeOrder(PlaceOrderDtoRequest placeOrderDtoRequest,Long clientId);
}
