package com.restaurant.court_service.infrastructure.configuration;

import com.restaurant.court_service.domain.api.IDishServicePort;
import com.restaurant.court_service.domain.api.IRestaurantServicePort;
import com.restaurant.court_service.domain.spi.ICategoryPersistencePort;
import com.restaurant.court_service.domain.spi.IDishPersistencePort;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.domain.usecase.DishUseCase;
import com.restaurant.court_service.domain.usecase.RestaurantUseCase;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.DishJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.PageMapper;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.repository.ICategoryRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IDishRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final PageMapper pageMapper;

    private final ICategoryRepository categoryRepository;

    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository, restaurantRepository, categoryRepository,dishEntityMapper);
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
    public IRestaurantServicePort categoryServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

}
