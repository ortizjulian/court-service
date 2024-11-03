package com.restaurant.court_service.domain.api;

import com.restaurant.court_service.domain.model.PlaceOrder;

public interface IOrderServicePort {
    void placeOrder(PlaceOrder placeOrder);
}
