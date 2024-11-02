package com.restaurant.court_service.domain.usecase;

import com.restaurant.court_service.domain.api.IDishServicePort;
import com.restaurant.court_service.domain.exception.CategoryNotFoundException;
import com.restaurant.court_service.domain.exception.DishNotFoundException;
import com.restaurant.court_service.domain.exception.RestaurantNotFoundException;
import com.restaurant.court_service.domain.exception.UpdateDishException;
import com.restaurant.court_service.domain.model.Dish;
import com.restaurant.court_service.domain.model.DishUpdate;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.domain.spi.ICategoryPersistencePort;
import com.restaurant.court_service.domain.spi.IDishPersistencePort;
import com.restaurant.court_service.domain.spi.IRestaurantPersistencePort;
import com.restaurant.court_service.domain.utils.PaginationValidator;
import com.restaurant.court_service.utils.Constants;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void createDish(Dish dish) {
        if(!categoryPersistencePort.existById(dish.getCategory().getId())){
            throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND + dish.getCategory().getId());
        }

        if(!restaurantPersistencePort.existById(dish.getRestaurant().getId())){
            throw new RestaurantNotFoundException(Constants.EXCEPTION_RESTAURANT_NOT_FOUND + dish.getRestaurant().getId());
        }
        dish.setActive(true);
        dishPersistencePort.createDish(dish);
    }

    @Override
    public void updateDish(DishUpdate dishUpdate) {
        if(dishUpdate.getPrice() == null && dishUpdate.getDescription()==null){
            throw new UpdateDishException();
        }

        if(!dishPersistencePort.existById(dishUpdate.getId())){
            throw new DishNotFoundException(Constants.EXCEPTION_DISH_NOT_FOUND + dishUpdate.getId());
        }

        dishPersistencePort.updateDish(dishUpdate);
    }

    @Override
    public void changeDishStatus(Long id, boolean status) {
        if(!dishPersistencePort.existById(id)){
            throw new DishNotFoundException(Constants.EXCEPTION_DISH_NOT_FOUND + id);
        }
        dishPersistencePort.changeDishStatus(id,status);
    }

    @Override
    public PageCustom<Dish> getAllDishes(Integer page, Integer size, String sortDirection, String sortBy, String restaurantId, String categoryId) {
        PaginationValidator.validatePagination(page,size,sortDirection);

        if (restaurantId != null && !restaurantId.isBlank() && !restaurantPersistencePort.existById(Long.parseLong(restaurantId))) {
            throw new RestaurantNotFoundException(Constants.EXCEPTION_RESTAURANT_NOT_FOUND + restaurantId);

        }

        if (categoryId != null && !categoryId.isBlank() && !categoryPersistencePort.existById(Long.parseLong(categoryId))) {
            throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND  + categoryId);
        }
        return this.dishPersistencePort.getAllDishes(page,size,sortDirection,sortBy,restaurantId,categoryId);
    }
}
