package com.restaurant.court_service.application.dto;

public class RestaurantDtoResponse {
    private String name;
    private String urlLogo;

    public RestaurantDtoResponse(String name, String urlLogo) {
        this.name = name;
        this.urlLogo = urlLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
