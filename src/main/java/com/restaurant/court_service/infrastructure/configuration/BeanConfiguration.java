package com.restaurant.court_service.infrastructure.configuration;

import com.restaurant.court_service.domain.api.IRestaurantServicePort;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.domain.usecase.RestaurantUseCase;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper );
    }

    @Bean
    public IRestaurantServicePort categoryServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

}
