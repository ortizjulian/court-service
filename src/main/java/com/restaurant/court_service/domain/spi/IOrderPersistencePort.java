package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.Order;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.PlaceOrder;

public interface IOrderPersistencePort {
    void createOrder(PlaceOrder placeOrder);
    boolean existById(Long id);
    boolean clientHasAlreadyAnOrder(Long clientId);
    PageCustom<Order> getAllOrders(Integer page, Integer size, String orderStatus, Long restaurantId);

    boolean orderIsPending(Long id);

    void assignOrder(Long employeeId, Long orderId);
}
