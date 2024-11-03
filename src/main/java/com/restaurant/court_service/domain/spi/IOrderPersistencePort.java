package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.PlaceOrder;

public interface IOrderPersistencePort {
    void createOrder(PlaceOrder placeOrder);

    boolean clientHasAlreadyAnOrder(Long clientId);
}
