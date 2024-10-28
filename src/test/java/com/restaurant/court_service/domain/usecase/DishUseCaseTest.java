package com.restaurant.court_service.domain.usecase;


import com.restaurant.court_service.domain.exception.CategoryNotFoundException;
import com.restaurant.court_service.domain.exception.RestaurantNotFoundException;
import com.restaurant.court_service.domain.model.Category;
import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.Restaurant;
import com.restaurant.court_service.domain.spi.ICategoryPersistencePort;
import com.restaurant.court_service.domain.spi.IDishPersistencePort;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    Dish dish = new Dish( "Pizza", 20000, "pizza", "http://pizza.com", new Restaurant(1L, "Frisby", "1134134", "Cra 77", "+304444444", "http://logo.com"), new Category(1L, "Entradas", "Aperitivos y entradas"),true);

    @Test
    void DishUseCase_CreateDish_WhenCategoryNotFound_ShouldThrowCategoryNotFoundException() {

        Mockito.when(categoryPersistencePort.existById(Mockito.anyLong())).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> {
            dishUseCase.createDish(dish);
        });

        Mockito.verify(dishPersistencePort, Mockito.never()).createDish(Mockito.any(Dish.class));
    }

    @Test
    void DishUseCase_CreateDish_WhenRestaurantNotFound_ShouldThrowRestaurantNotFoundException() {
        Mockito.when(categoryPersistencePort.existById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(restaurantPersistencePort.existById(Mockito.anyLong())).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> {
            dishUseCase.createDish(dish);
        });

        Mockito.verify(dishPersistencePort, Mockito.never()).createDish(Mockito.any(Dish.class));
    }

    @Test
    void DishUseCase_CreateDish_ShouldCallCreateDishOnPersistencePort() {

        Mockito.when(categoryPersistencePort.existById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(restaurantPersistencePort.existById(Mockito.anyLong())).thenReturn(true);

        dishUseCase.createDish(dish);

        Mockito.verify(dishPersistencePort).createDish(dish);
    }
}