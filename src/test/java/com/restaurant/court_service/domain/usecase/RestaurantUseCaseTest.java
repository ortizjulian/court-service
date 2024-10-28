package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.exception.RestaurantDuplicateNitException;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    Restaurant restaurant = new Restaurant( 1L,"Frisby", "1134134", "Cra 77" , "+304444444", "http://logo.com");

    @Test
    void RestaurantUseCase_CreateRestaurant_ShouldCallCreeteRestaurantOnPersistencePort() {
        Mockito.when(restaurantPersistencePort.existByNit(Mockito.anyString())).thenReturn(false);

        Mockito.doNothing().when(restaurantPersistencePort).createRestaurant(restaurant);
        restaurantUseCase.createRestaurant(restaurant);
        Mockito.verify(restaurantPersistencePort).createRestaurant(restaurant);
    }

    @Test
    void RestaurantUseCase_CreateRestaurant_WhenCreatingRestaurantWithExistingNit_ShoulThrowRestaurantDuplicatedNit() {
        Mockito.when(restaurantPersistencePort.existByNit(Mockito.anyString())).thenReturn(true);

        assertThrows(RestaurantDuplicateNitException.class, () -> {
            restaurantUseCase.createRestaurant(restaurant);
        });

        Mockito.verify(restaurantPersistencePort, Mockito.never()).createRestaurant(Mockito.any(Restaurant.class));
    }

}