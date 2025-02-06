package com.restaurant.court_service.domain.spi;

public interface ISecurityPersistencePort {
    void setToken(String jwtToken);
    String getToken();
    void removeToken();
}
