package com.restaurant.court_service.domain.api;

import com.restaurant.court_service.application.dto.DishDtoResponse;
import com.restaurant.court_service.domain.model.Order;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.PlaceOrder;

public interface IOrderServicePort {
    void placeOrder(PlaceOrder placeOrder);

    PageCustom<Order> getAllOrders(Integer page, Integer size, String orderStatus, Long clientId);
}
