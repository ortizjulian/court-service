package com.restaurant.court_service.infrastructure.configuration.feign;

import com.restaurant.court_service.infrastructure.output.feign.utils.RetreiveMessageErrorDecoder;
import com.restaurant.court_service.infrastructure.output.security.adapter.SecurityAdapter;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {

    private final SecurityAdapter securityAdapter;

    @Bean
    Logger.Level feignLoggerLevel() {
        return  Logger.Level.FULL;
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor(securityAdapter);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetreiveMessageErrorDecoder();
    }
}
