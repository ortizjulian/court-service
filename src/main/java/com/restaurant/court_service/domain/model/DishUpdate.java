package com.restaurant.court_service.domain.model;

public class DishUpdate {
    private Long id;
    private Integer price;
    private String description;

    public DishUpdate(Long id, Integer price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
