package com.restaurant.court_service.infrastructure.output.jpa.adapter;

import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.infrastructure.output.jpa.entity.RestaurantEntity;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.PageMapper;
import com.restaurant.court_service.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final PageMapper pageMapper;

    @Override
    public void createRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public Boolean existById(Long id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public Boolean existByNit(String nit) {
        return restaurantRepository.existsByNit(nit);
    }

    @Override
    public PageCustom<Restaurant> getAllRestaurants(Integer page, Integer size, String sortDirection, String sortBy) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pageable);
        return pageMapper.toRestaurantPageCustom(restaurantPage);
    }

    @Override
    public Long employeeRestaurant(Long clientId) {
        return restaurantRepository.findRestaurantIdByEmployeeId(clientId)
                .orElse(null);
    }
}
