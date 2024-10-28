package com.restaurant.court_service.utils;

public class Constants {
    private Constants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }
    //Exceptions messages
    public static final String RESPONSE_MESSAGE_KEY = "Message";

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
    public static final String EXCEPTION_RESTAURANT_NAME_INVALID = "The restaurant name must contain at least one letter and cannot consist only of numbers.";

    public static final int MAX_RESTAURANT_PHONE_CHARACTERS = 13;

    public static final String NIT_REG_EXP = "^[0-9]+$";
    public static final String PHONE_REG_EXP = "^\\+?[0-9]{1,13}$";
    public static final String URL_REG_EXP = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
    public static final String RESTAURANT_NAME_REG_EXP = "^(?=.*[a-zA-Z]).+$";

    public static final String EXCEPTION_RESTAURANT_DUPLICATED_NIT = "There is already a restaurant with this NIT.";

}

