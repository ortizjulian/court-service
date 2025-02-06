package com.restaurant.court_service.domain.api;

public interface ISecurityServicePort {
    void setToken(String token);
    void removeToken();
}
