package com.restaurant.court_service.infrastructure.output.feign.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserPhoneResponseDto {
    private String phone;
}
