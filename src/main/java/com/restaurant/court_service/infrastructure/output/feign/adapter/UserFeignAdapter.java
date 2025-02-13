package com.restaurant.court_service.infrastructure.output.feign.adapter;

import com.restaurant.court_service.domain.spi.IUserPersistencePort;
import com.restaurant.court_service.infrastructure.output.feign.client.UserFeignClient;
import com.restaurant.court_service.infrastructure.output.feign.dto.UserPhoneResponseDto;
import com.restaurant.court_service.infrastructure.output.feign.exceptions.NotFoundException;
import com.restaurant.court_service.utils.FeignConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class UserFeignAdapter implements IUserPersistencePort {

    private final UserFeignClient userFeignClient;

    @Override
    public String getUserPhoneNumber(Long userId) {
        ResponseEntity<UserPhoneResponseDto> response = userFeignClient.getUserPhone(userId);

        if (response != null && response.getBody() != null) {
            UserPhoneResponseDto userPhoneResponseDto = response.getBody();
            return userPhoneResponseDto.getPhone();
        }
        else {
            throw new NotFoundException(FeignConstants.USER_PHONE_NOT_FOUND_EXCEPTION);
        }
    }
}
