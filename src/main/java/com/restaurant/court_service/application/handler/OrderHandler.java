package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.application.mapper.PlaceOrderDtoRequestMapper;
import com.restaurant.court_service.domain.api.IOrderServicePort;
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
    @Override
    public void placeOrder(PlaceOrderDtoRequest placeOrderDtoRequest, Long clientId) {
        PlaceOrder placeOrder = placeOrderDtoRequestMapper.toPlaceOrder(placeOrderDtoRequest);
        placeOrder.setClientId(clientId);
        orderServicePort.placeOrder(placeOrder);
    }

}
