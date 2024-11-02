package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.exception.PaginationParametersInvalidException;
import com.restaurant.court_service.domain.exception.RestaurantDuplicateNitException;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void RestaurantUseCase_GetAllRestaurants_WhenPageIsNegative_ShouldThrowPaginationParametersInvalidException() {

        int invalidPage = -1;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        assertThrows(PaginationParametersInvalidException.class, () -> {
            restaurantUseCase.getAllRestaurants(invalidPage, size, sortDirection, sortBy);
        });
    }

    @Test
    void RestaurantUseCase_GetAllRestaurants_ShouldReturnCategoriesSortedByNameAscending() {

        int page = 0;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add( new Restaurant(1L, "Frisby", "3424342", "Cra 22", "3434232342", "https://frisby.com"));
        restaurants.add( new Restaurant(2L, "Kokoriko", "3424342", "Cra 22", "3434232342", "https://frisby.com"));

        PageCustom<Restaurant> mockPage = new PageCustom<>();
        mockPage.setContent(restaurants);
        mockPage.setTotalElements(3L);
        mockPage.setTotalPages(1);

        Mockito.when(restaurantPersistencePort.getAllRestaurants(page, size, sortDirection, sortBy))
                .thenReturn(mockPage);


        PageCustom<Restaurant> result = restaurantUseCase.getAllRestaurants(page, size, sortDirection, sortBy);


        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Frisby", result.getContent().get(0).getName());
        assertEquals("Kokoriko", result.getContent().get(1).getName());

        Mockito.verify(restaurantPersistencePort).getAllRestaurants(page, size, sortDirection, sortBy);
    }
}