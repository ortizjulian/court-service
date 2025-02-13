package com.restaurant.court_service.infrastructure.output.feign.adapter;

import com.restaurant.court_service.domain.model.StateUpdate;
import com.restaurant.court_service.domain.model.Traceability;
import com.restaurant.court_service.domain.spi.ITraceabilityPersistencePort;
import com.restaurant.court_service.infrastructure.output.feign.client.TraceabilityFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceabilityFeignAdapter implements ITraceabilityPersistencePort {

    private final TraceabilityFeignClient traceabilityFeignClient;

    @Override
    public void createTraceability(Traceability traceability) {
        traceabilityFeignClient.createTraceability(traceability);
    }

    @Override
    public void updateOrderStatus(StateUpdate stateUpdate) {
        traceabilityFeignClient.updateOrderState(stateUpdate);
    }
}
