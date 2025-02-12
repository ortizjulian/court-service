package com.restaurant.court_service.domain.spi;

import com.restaurant.court_service.domain.model.Traceability;

public interface ITraceabilityPersistencePort {
    void createTraceability(Traceability traceability);
}
