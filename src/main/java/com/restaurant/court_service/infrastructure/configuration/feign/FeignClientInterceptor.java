package com.restaurant.court_service.infrastructure.configuration.feign;

import com.restaurant.court_service.infrastructure.output.security.adapter.SecurityAdapter;
import com.restaurant.court_service.utils.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

    private final SecurityAdapter securityAdapter;

    @Override
    public void apply(RequestTemplate template) {

        String token = securityAdapter.getToken();

        if (token != null && !token.isEmpty()) {
            template.header(SecurityConstants.AUTHORIZATION, token);
        }

    }
}
