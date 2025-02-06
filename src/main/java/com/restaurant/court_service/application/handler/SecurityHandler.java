package com.restaurant.court_service.application.handler;

import com.restaurant.court_service.domain.api.ISecurityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityHandler implements ISecurityHandler{

    private final ISecurityServicePort securityServicePort;

    @Override
    public void setToken(String token) {
        securityServicePort.setToken(token);
    }

    @Override
    public void removeToken() {
        securityServicePort.removeToken();
    }
}
