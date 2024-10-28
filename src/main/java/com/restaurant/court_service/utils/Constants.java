package com.restaurant.court_service.utils;

public class Constants {
    private Constants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";
    //Restaurant
    public static final String EXCEPTION_RESTAURANT_NAME_MANDATORY = "The restaurant name is mandatory.";
    public static final String EXCEPTION_RESTAURANT_NIT_MANDATORY = "The NIT is mandatory.";
    public static final String EXCEPTION_RESTAURANT_NIT_NUMERIC = "The NIT must contain only numeric characters.";
    public static final String EXCEPTION_RESTAURANT_ADDRESS_MANDATORY = "The address is mandatory.";
    public static final String EXCEPTION_RESTAURANT_PHONE_MANDATORY = "The phone number is mandatory.";
    public static final String EXCEPTION_RESTAURANT_PHONE_MAX_LENGTH = "The phone number must not exceed 13 characters.";
    public static final String EXCEPTION_RESTAURANT_PHONE_INVALID = "The phone number must contain only numeric characters and can start with '+'.";
    public static final String EXCEPTION_RESTAURANT_URL_LOGO_MANDATORY = "The logo URL is mandatory.";
    public static final String EXCEPTION_RESTAURANT_URL_LOGO_INVALID = "The logo URL format is invalid.";

    public static final int MAX_RESTAURANT_PHONE_CHARACTERS = 13;

    public static final String NIT_REG_EXP = "^[0-9]+$"; // Solo números
    public static final String PHONE_REG_EXP = "^\\+?[0-9]{1,13}$"; // Solo números con opcional '+'
    public static final String URL_REG_EXP = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$"; // Formato URL estándar
}

