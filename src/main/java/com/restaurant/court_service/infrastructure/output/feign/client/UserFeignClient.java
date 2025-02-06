package com.restaurant.court_service.infrastructure.output.feign.client;

import com.restaurant.court_service.infrastructure.configuration.feign.FeignClientConfig;
import com.restaurant.court_service.infrastructure.output.feign.dto.UserPhoneResponseDto;
import com.restaurant.court_service.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = FeignConstants.FEIGN_USER_NAME, url = FeignConstants.FEIGN_USER_PATH, configuration = FeignClientConfig.class)
public interface UserFeignClient {
    @GetMapping(FeignConstants.PATH_GET_USER_PHONE)
    ResponseEntity<UserPhoneResponseDto> getUserPhone(@PathVariable(FeignConstants.USER_ID) Long userId);

}
