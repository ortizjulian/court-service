package com.restaurant.court_service.utils;

public class Constants {
    private Constants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }
    //Exceptions messages
    public static final String RESPONSE_MESSAGE_KEY = "Message";

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    // Pagination defaults
    public static final Integer PAGINATION_ZERO = 0;
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";
    public static final String DEFAULT_SORT_BY = "name";
    public static final String SORT_DIRECTION_ASC = "ASC";
    public static final String SORT_DIRECTION_DESC = "DESC";

    //Pagination Exceptions
    public static final String EXCEPTION_PAGE_NUMBER_NEGATIVE = "Page number cannot be negative.";
    public static final String EXCEPTION_PAGE_SIZE_NEGATIVE = "Page size cannot be negative.";
    public static final String EXCEPTION_SORT_DIRECTION_INVALID = "Sort direction must be 'ASC' or 'DESC'.";


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
    public static final String EXCEPTION_RESTAURANT_NOT_FOUND = "Restaurant not found with ID: ";
    public static final String EXCEPTION_CATEGORY_NOT_FOUND = "Category not found with ID: ";

    public static final String EXCEPTION_DISH_ID_MANDATORY = "The dish id is mandatory.";
    public static final String EXCEPTION_DISH_NAME_MANDATORY = "The dish name is mandatory.";
    public static final String EXCEPTION_DISH_PRICE_MANDATORY = "The dish price must be a positive integer greater than 0.";
    public static final String EXCEPTION_DISH_DESCRIPTION_MANDATORY = "The dish description is mandatory.";
    public static final String EXCEPTION_DISH_IMAGE_URL_MANDATORY = "The dish image URL is mandatory.";
    public static final String EXCEPTION_DISH_IMAGE_URL_INVALID = "The dish image URL is invalid.";
    public static final String EXCEPTION_DISH_RESTAURANT_MANDATORY = "The restaurant ID is mandatory.";
    public static final String EXCEPTION_DISH_CATEGORY_MANDATORY = "The category ID is mandatory.";
    public static final String EXCEPTION_DISH_NOT_FOUND = "Dish not found with ID: ";
    public static final String EXCEPTION_DISH_UPDATE_MANDATORY_FIELDS = "At least one of price or description must be provided for update.";

    public static final String DEFAULT_RESTAURANT_ID = "";
    public static final String DEFAULT_CATEGORY_ID = "";


    //MAPPERS - ARTICLE
    public static final String RESTAURANT_ENTITY = "restaurant";
    public static final String CATEGORY_ENTITY = "category";
    public static final String DISH_ID = "id";
}

