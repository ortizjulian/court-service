package com.restaurant.court_service.domain.spi;

public interface IMessagingPersistencePort {
    void notifyClient(String phoneNumber, Long orderId);

    void checkCode(Long orderId, String code);
}
