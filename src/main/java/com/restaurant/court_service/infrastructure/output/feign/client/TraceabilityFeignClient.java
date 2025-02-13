package com.restaurant.court_service.infrastructure.output.feign.client;

import com.restaurant.court_service.domain.model.StateUpdate;
import com.restaurant.court_service.domain.model.Traceability;
import com.restaurant.court_service.infrastructure.configuration.feign.FeignClientConfig;
import com.restaurant.court_service.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = FeignConstants.FEIGN_TRACEABILITY_NAME, url = FeignConstants.PATH_TRACEABILITY_URL, configuration = FeignClientConfig.class)
public interface TraceabilityFeignClient {

    @PostMapping(FeignConstants.PATH_CREATE_TRACEABILITY)
    ResponseEntity<Void> createTraceability(@RequestBody Traceability traceability);

    @PostMapping(FeignConstants.PATH_TRACEABILITY_UPDATE_STATE)
    ResponseEntity<Void> updateOrderState(@RequestBody StateUpdate stateUpdate);

}
