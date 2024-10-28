package com.restaurant.court_service.application.dto;

import com.restaurant.court_service.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DishDtoUpdate {

    @NotNull(message = Constants.EXCEPTION_DISH_ID_MANDATORY)
    private Long id;

    @Positive(message = Constants.EXCEPTION_DISH_PRICE_MANDATORY)
    private Integer price;

    private String description;

    public DishDtoUpdate(Long id, Integer price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public @NotNull(message = Constants.EXCEPTION_DISH_ID_MANDATORY) Long getId() {
        return id;
    }

    public void setId(@NotNull(message = Constants.EXCEPTION_DISH_ID_MANDATORY) Long id) {
        this.id = id;
    }

    public @Positive(message = Constants.EXCEPTION_DISH_PRICE_MANDATORY) Integer getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = Constants.EXCEPTION_DISH_PRICE_MANDATORY) Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}