package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.OrderDtoResponse;
import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.domain.model.PageCustom;

public interface IOrderHandler {
    void placeOrder(PlaceOrderDtoRequest placeOrderDtoRequest,Long clientId);

    PageCustom<OrderDtoResponse> getAllOrders(Integer page, Integer size, String orderStatus, Long clientId);

    void assignOrder(Long employeeId, Long orderId);

    void finishOrder(Long orderId);

    void deliverOrder(Long orderId, String code);
}
