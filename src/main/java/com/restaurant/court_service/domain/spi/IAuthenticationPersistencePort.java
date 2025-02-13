package com.restaurant.court_service.domain.spi;

public interface IAuthenticationPersistencePort {
    Long getAuthenticatedUserId();
    String getAuthenticatedUserEMail();
}
