package com.restaurant.court_service.infrastructure.output.feign.client;

import com.restaurant.court_service.infrastructure.configuration.feign.FeignClientConfig;
import com.restaurant.court_service.infrastructure.output.feign.dto.NotifyClientDto;
import com.restaurant.court_service.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = FeignConstants.FEIGN_MESSAGING_NAME, url = FeignConstants.PATH_MESSAGING_URL, configuration = FeignClientConfig.class)
public interface MessagingFeignClient {
    @PostMapping(FeignConstants.PATH_NOTIFY_CLIENT)
     ResponseEntity<Void> notifyClient(@RequestBody NotifyClientDto notifyClientDto);
}
