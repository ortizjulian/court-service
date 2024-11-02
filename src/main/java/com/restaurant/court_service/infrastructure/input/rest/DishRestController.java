package com.restaurant.court_service.infrastructure.input.rest;

import com.restaurant.court_service.application.dto.DishDtoRequest;
import com.restaurant.court_service.application.dto.DishDtoResponse;
import com.restaurant.court_service.application.dto.DishDtoUpdate;
import com.restaurant.court_service.application.dto.RestaurantDtoResponse;
import com.restaurant.court_service.application.handler.IDishHandler;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dish")
@RequiredArgsConstructor
@Validated
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Retrieve all Dishes", description = "Returns a list of all Dishes available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of Dishes"),
    })
    @GetMapping
    public ResponseEntity<PageCustom<DishDtoResponse>> getAllDishes(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) Integer size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = Constants.DEFAULT_RESTAURANT_ID) String restaurantId,
            @RequestParam(defaultValue = Constants.DEFAULT_CATEGORY_ID) String categoryId){
        return ResponseEntity.ok(dishHandler.getAllDishes(page,size,sortDirection,sortBy,restaurantId,categoryId));
    }

    @Operation(summary = "Create a new dish", description = "Adds a new dish to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Restaurant or Category Not Found"),
    })
    @PostMapping
    public ResponseEntity<Void> createDish(@Valid @RequestBody DishDtoRequest dishDtoRequest) {
        dishHandler.createDish(dishDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateDish(@Valid @RequestBody DishDtoUpdate dishDtoUpdate) {
        dishHandler.updateDish(dishDtoUpdate);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateDish(@PathVariable Long id) {
        dishHandler.changeDishStatus(id, true);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateDish(@PathVariable Long id) {
        dishHandler.changeDishStatus(id, false);
        return ResponseEntity.noContent().build();
    }
}
