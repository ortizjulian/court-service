package com.restaurant.court_service.infrastructure.input.rest;

import com.restaurant.court_service.application.dto.RestaurantDtoRequest;
import com.restaurant.court_service.application.dto.RestaurantDtoResponse;
import com.restaurant.court_service.application.handler.IRestaurantHandler;
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

import java.util.List;

@RestController
@RequestMapping("restaurant")
@RequiredArgsConstructor
@Validated
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Retrieve all Articles", description = "Returns a list of all Article available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of Article"),
    })
    @GetMapping
    public ResponseEntity<PageCustom<RestaurantDtoResponse>> getAllArticles(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) Integer size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy
    ){
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants(page,size,sortDirection,sortBy));
    }


    @Operation(summary = "Create a new Restaurant", description = "Adds a new Restaurant to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "A restaurant already exists with this NIT"),
    })
    @PostMapping
    public ResponseEntity<Void> createRestaurant(@Valid @RequestBody RestaurantDtoRequest restaurantDtoRequest) {
        restaurantHandler.createRestaurant(restaurantDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
