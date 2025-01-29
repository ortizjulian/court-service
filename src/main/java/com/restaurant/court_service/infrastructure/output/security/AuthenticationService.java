package com.restaurant.court_service.infrastructure.output.security;

import com.restaurant.court_service.infrastructure.output.security.entity.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        return userDetails.getId();
    }
}
