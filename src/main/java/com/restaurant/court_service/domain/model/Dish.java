package com.restaurant.court_service.domain.model;

public class Dish {
    private String name;
    private Integer price;
    private String description;
    private String urlImage;
    private Restaurant restaurant;
    private Category category;
    private boolean active;

    public Dish(String name, Integer price, String description, String urlImage, Restaurant restaurant,Category category, boolean active) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.restaurant = restaurant;
        this.active = active;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
