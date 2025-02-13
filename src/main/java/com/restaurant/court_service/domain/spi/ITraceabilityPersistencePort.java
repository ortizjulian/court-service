package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.Traceability;
import com.restaurant.court_service.domain.model.StateUpdate;

public interface ITraceabilityPersistencePort {
    void createTraceability(Traceability traceability);
    void updateOrderStatus(StateUpdate stateUpdate);
}
