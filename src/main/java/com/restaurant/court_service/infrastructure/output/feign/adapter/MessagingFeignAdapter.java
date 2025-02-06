package com.restaurant.court_service.infrastructure.output.feign.adapter;

import com.restaurant.court_service.domain.spi.IMessagingPersistencePort;
import com.restaurant.court_service.infrastructure.output.feign.client.MessagingFeignClient;
import com.restaurant.court_service.infrastructure.output.feign.dto.NotifyClientDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagingFeignAdapter implements IMessagingPersistencePort {

    private final MessagingFeignClient messagingFeignClient;

    @Override
    public void notifyClient(String phoneNumber, Long orderId) {
        NotifyClientDto notifyClientDto = new NotifyClientDto();
        notifyClientDto.setPhoneNumber(phoneNumber);
        notifyClientDto.setOrderId(orderId);

        messagingFeignClient.notifyClient(notifyClientDto);
    }
}
