package com.restaurant.court_service.infrastructure.input.rest;

import com.restaurant.court_service.application.dto.DishDtoRequest;
import com.restaurant.court_service.application.dto.DishDtoUpdate;
import com.restaurant.court_service.application.handler.IDishHandler;
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
}
