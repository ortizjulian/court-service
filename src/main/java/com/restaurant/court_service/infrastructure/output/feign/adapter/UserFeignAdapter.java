package com.restaurant.court_service.infrastructure.output.feign.adapter;

import com.restaurant.court_service.domain.spi.IUserPersistencePort;
import com.restaurant.court_service.infrastructure.output.feign.client.UserFeignClient;
import com.restaurant.court_service.infrastructure.output.feign.dto.UserPhoneResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class UserFeignAdapter implements IUserPersistencePort {

    private final UserFeignClient userFeignClient;

    @Override
    public String getUserPhoneNumber(Long userId) {
        ResponseEntity<UserPhoneResponseDto> response = userFeignClient.getUserPhone(userId);
        UserPhoneResponseDto userPhoneResponseDto = response.getBody();
        return userPhoneResponseDto.getPhone();
    }
}
