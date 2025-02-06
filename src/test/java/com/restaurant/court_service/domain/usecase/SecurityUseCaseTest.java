package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.spi.ISecurityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class SecurityUseCaseTest {

    @Mock
    private ISecurityPersistencePort securityPersistencePort;

    @InjectMocks
    private SecurityUseCase securityUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void setToken_ShouldCallSecurityPersistencePortSetToken() {
        String token = "sampleToken";

        securityUseCase.setToken(token);

        Mockito.verify(securityPersistencePort).setToken(token);
    }

    @Test
    void removeToken_ShouldCallSecurityPersistencePortRemoveToken() {
        securityUseCase.removeToken();

        Mockito.verify(securityPersistencePort).removeToken();
    }

}