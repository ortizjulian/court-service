package com.restaurant.court_service.application.dto;

import com.restaurant.court_service.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

public class DishDtoRequest {

    @NotBlank(message = Constants.EXCEPTION_DISH_NAME_MANDATORY)
    private String name;

    @Positive(message = Constants.EXCEPTION_DISH_PRICE_MANDATORY)
    private Integer price;

    @NotBlank(message = Constants.EXCEPTION_DISH_DESCRIPTION_MANDATORY)
    private String description;

    @NotBlank(message = Constants.EXCEPTION_DISH_IMAGE_URL_MANDATORY)
    @URL(message = Constants.EXCEPTION_DISH_IMAGE_URL_INVALID)
    private String urlImage;

    @NotNull(message = Constants.EXCEPTION_DISH_RESTAURANT_MANDATORY)
    private Long restaurantId;

    @NotNull(message = Constants.EXCEPTION_DISH_CATEGORY_MANDATORY)
    private Long categoryId;

    public DishDtoRequest(String name, Integer price, String description, String urlImage, Long restaurantId, Long categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
    }

    public @NotBlank(message = Constants.EXCEPTION_DISH_NAME_MANDATORY) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = Constants.EXCEPTION_DISH_NAME_MANDATORY) String name) {
        this.name = name;
    }

    public @Positive(message = Constants.EXCEPTION_DISH_PRICE_MANDATORY) Integer getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = Constants.EXCEPTION_DISH_PRICE_MANDATORY) Integer price) {
        this.price = price;
    }

    public @NotBlank(message = Constants.EXCEPTION_DISH_DESCRIPTION_MANDATORY) String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = Constants.EXCEPTION_DISH_DESCRIPTION_MANDATORY) String description) {
        this.description = description;
    }

    public @NotBlank(message = Constants.EXCEPTION_DISH_IMAGE_URL_MANDATORY) @URL(message = Constants.EXCEPTION_DISH_IMAGE_URL_INVALID) String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(@NotBlank(message = Constants.EXCEPTION_DISH_IMAGE_URL_MANDATORY) @URL(message = Constants.EXCEPTION_DISH_IMAGE_URL_INVALID) String urlImage) {
        this.urlImage = urlImage;
    }

    public @NotNull(message = Constants.EXCEPTION_DISH_RESTAURANT_MANDATORY) Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(@NotNull(message = Constants.EXCEPTION_DISH_RESTAURANT_MANDATORY) Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public @NotNull(message = Constants.EXCEPTION_DISH_CATEGORY_MANDATORY) Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(message = Constants.EXCEPTION_DISH_CATEGORY_MANDATORY) Long categoryId) {
        this.categoryId = categoryId;
    }
}
