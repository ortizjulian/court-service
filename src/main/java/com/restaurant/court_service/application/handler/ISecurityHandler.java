package com.restaurant.court_service.application.handler;

public interface ISecurityHandler {
    void setToken(String token);
    void removeToken();
}
