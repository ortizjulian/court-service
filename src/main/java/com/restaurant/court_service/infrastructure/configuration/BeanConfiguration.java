package com.restaurant.court_service.infrastructure.configuration;

import com.restaurant.court_service.domain.api.*;
import com.restaurant.court_service.domain.spi.*;
import com.restaurant.court_service.domain.usecase.*;
import com.restaurant.court_service.infrastructure.output.feign.adapter.MessagingFeignAdapter;
import com.restaurant.court_service.infrastructure.output.feign.adapter.UserFeignAdapter;
import com.restaurant.court_service.infrastructure.output.feign.client.MessagingFeignClient;
import com.restaurant.court_service.infrastructure.output.feign.client.UserFeignClient;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.DishJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.OrderJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.PageMapper;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.repository.*;
import com.restaurant.court_service.infrastructure.output.security.adapter.AuthenticationAdapter;
import com.restaurant.court_service.infrastructure.output.security.adapter.SecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final MessagingFeignClient messagingFeignClient;
    private final UserFeignClient userFeignClient;

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final PageMapper pageMapper;

    private final ICategoryRepository categoryRepository;

    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    private final IOrderRepository orderRepository;
    private final IOrderDishesRepository orderDishesRepository;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository, restaurantRepository, categoryRepository,dishEntityMapper, pageMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort(),restaurantPersistencePort(), categoryPersistencePort());
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper,pageMapper );
    }

    @Bean
    public IMessagingPersistencePort messagingPersistencePort(){
        return new MessagingFeignAdapter(messagingFeignClient);
    }

    @Bean IUserPersistencePort userPersistencePort(){
        return new UserFeignAdapter(userFeignClient);
    }

    @Bean
    public IRestaurantServicePort categoryServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(restaurantPersistencePort(),dishPersistencePort(),orderPersistencePort(),messagingPersistencePort(),userPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(restaurantRepository,orderRepository,dishRepository,orderDishesRepository,pageMapper);
    }

    @Bean
    public ISecurityServicePort securityServicePort() {
        return new SecurityUseCase(securityPersistencePort());
    }

    @Bean
    public ISecurityPersistencePort securityPersistencePort(){
        return new SecurityAdapter();
    }

    @Bean public IAuthenticationServicePort authenticationServicePort(){
        return new AuthenticationUseCase(authenticationPersistencePort());
    }

    @Bean
    public IAuthenticationPersistencePort authenticationPersistencePort(){
        return new AuthenticationAdapter();
    }
}
