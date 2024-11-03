package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.OrderDtoResponse;
import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.application.mapper.PageDtoMapper;
import com.restaurant.court_service.application.mapper.PlaceOrderDtoRequestMapper;
import com.restaurant.court_service.domain.api.IOrderServicePort;
import com.restaurant.court_service.domain.model.Order;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.PlaceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler{

    private final IOrderServicePort orderServicePort;
    private final PlaceOrderDtoRequestMapper placeOrderDtoRequestMapper;
    private final PageDtoMapper pageDtoMapper;

    @Override
    public void placeOrder(PlaceOrderDtoRequest placeOrderDtoRequest, Long clientId) {
        PlaceOrder placeOrder = placeOrderDtoRequestMapper.toPlaceOrder(placeOrderDtoRequest);
        placeOrder.setClientId(clientId);
        orderServicePort.placeOrder(placeOrder);
    }

    @Override
    public PageCustom<OrderDtoResponse> getAllOrders(Integer page, Integer size, String orderStatus, Long clientId) {
        PageCustom<Order> orderPage = this.orderServicePort.getAllOrders(page,size,orderStatus,clientId);
        return pageDtoMapper.toOrderDtoPageCustom(orderPage);
    }

}
