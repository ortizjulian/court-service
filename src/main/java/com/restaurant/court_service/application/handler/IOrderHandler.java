package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.OrderDtoResponse;
import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.domain.model.PageCustom;

public interface IOrderHandler {
    void placeOrder(PlaceOrderDtoRequest placeOrderDtoRequest,Long clientId);

    PageCustom<OrderDtoResponse> getAllOrders(Integer page, Integer size, String orderStatus, Long clientId);
}
