package com.restaurant.court_service.application.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDtoResponse {

    private Long id;
    private String status;
    private LocalDate saleDate;
    private List<OrderDishDtoResponse> dishes;

    public OrderDtoResponse(Long id, String status, LocalDate saleDate, List<OrderDishDtoResponse> dishes) {
        this.id = id;
        this.status = status;
        this.saleDate = saleDate;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public List<OrderDishDtoResponse> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderDishDtoResponse> dishes) {
        this.dishes = dishes;
    }
}
