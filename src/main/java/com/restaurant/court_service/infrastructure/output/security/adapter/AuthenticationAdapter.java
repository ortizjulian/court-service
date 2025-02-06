package com.restaurant.court_service.infrastructure.output.security.adapter;

import com.restaurant.court_service.domain.spi.IAuthenticationPersistencePort;
import com.restaurant.court_service.infrastructure.output.security.entity.SecurityUser;
import com.restaurant.court_service.utils.SecurityConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAdapter implements IAuthenticationPersistencePort {

    @Override
    public Long getAuthenticatedUserId() {
        return getAuthenticatedUser().getId();
    }

    @Override
    public String getAuthenticatedUserPhone() {
        return getAuthenticatedUser().getPhone();
    }

    private SecurityUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException(SecurityConstants.USER_NOT_AUTHENTICATED);
        }
        return (SecurityUser) authentication.getPrincipal();
    }
}
