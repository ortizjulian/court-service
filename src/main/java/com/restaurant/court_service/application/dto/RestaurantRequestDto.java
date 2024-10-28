package com.restaurant.court_service.application.dto;

import com.restaurant.court_service.utils.Constants;
import jakarta.validation.constraints.*;

public class RestaurantRequestDto {

    @NotBlank(message = Constants.EXCEPTION_RESTAURANT_NAME_MANDATORY)
    private String name;

    @NotBlank(message = Constants.EXCEPTION_RESTAURANT_NIT_MANDATORY)
    @Pattern(regexp = Constants.NIT_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_NIT_NUMERIC)
    private String nit;

    @NotBlank(message = Constants.EXCEPTION_RESTAURANT_ADDRESS_MANDATORY)
    private String address;

    @NotBlank(message = Constants.EXCEPTION_RESTAURANT_PHONE_MANDATORY)
    @Size(max = Constants.MAX_RESTAURANT_PHONE_CHARACTERS, message = Constants.EXCEPTION_RESTAURANT_PHONE_MAX_LENGTH)
    @Pattern(regexp = Constants.PHONE_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_PHONE_INVALID)
    private String phone;

    @NotBlank(message = Constants.EXCEPTION_RESTAURANT_URL_LOGO_MANDATORY)
    @Pattern(regexp = Constants.URL_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_URL_LOGO_INVALID)
    private String urlLogo;

    public RestaurantRequestDto(String name, String nit, String address, String phone, String urlLogo) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
    }

    public @NotBlank(message = Constants.EXCEPTION_RESTAURANT_NAME_MANDATORY) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = Constants.EXCEPTION_RESTAURANT_NAME_MANDATORY) String name) {
        this.name = name;
    }

    public @NotBlank(message = Constants.EXCEPTION_RESTAURANT_NIT_MANDATORY) @Pattern(regexp = Constants.NIT_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_NIT_NUMERIC) String getNit() {
        return nit;
    }

    public void setNit(@NotBlank(message = Constants.EXCEPTION_RESTAURANT_NIT_MANDATORY) @Pattern(regexp = Constants.NIT_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_NIT_NUMERIC) String nit) {
        this.nit = nit;
    }

    public @NotBlank(message = Constants.EXCEPTION_RESTAURANT_ADDRESS_MANDATORY) String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = Constants.EXCEPTION_RESTAURANT_ADDRESS_MANDATORY) String address) {
        this.address = address;
    }

    public @NotBlank(message = Constants.EXCEPTION_RESTAURANT_PHONE_MANDATORY) @Size(max = Constants.MAX_RESTAURANT_PHONE_CHARACTERS, message = Constants.EXCEPTION_RESTAURANT_PHONE_MAX_LENGTH) @Pattern(regexp = Constants.PHONE_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_PHONE_INVALID) String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = Constants.EXCEPTION_RESTAURANT_PHONE_MANDATORY) @Size(max = Constants.MAX_RESTAURANT_PHONE_CHARACTERS, message = Constants.EXCEPTION_RESTAURANT_PHONE_MAX_LENGTH) @Pattern(regexp = Constants.PHONE_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_PHONE_INVALID) String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = Constants.EXCEPTION_RESTAURANT_URL_LOGO_MANDATORY) @Pattern(regexp = Constants.URL_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_URL_LOGO_INVALID) String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(@NotBlank(message = Constants.EXCEPTION_RESTAURANT_URL_LOGO_MANDATORY) @Pattern(regexp = Constants.URL_REG_EXP, message = Constants.EXCEPTION_RESTAURANT_URL_LOGO_INVALID) String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
