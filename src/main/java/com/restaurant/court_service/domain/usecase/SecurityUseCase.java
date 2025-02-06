package com.restaurant.court_service.domain.usecase;


import com.restaurant.court_service.domain.api.ISecurityServicePort;
import com.restaurant.court_service.domain.spi.ISecurityPersistencePort;

public class SecurityUseCase implements ISecurityServicePort {

    private final ISecurityPersistencePort securityPersistencePort;

    public SecurityUseCase(ISecurityPersistencePort securityPersistencePort) {
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public void setToken(String token) {
        securityPersistencePort.setToken(token);
    }

    @Override
    public void removeToken() {
        securityPersistencePort.removeToken();
    }
}
