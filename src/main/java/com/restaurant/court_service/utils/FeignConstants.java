package com.restaurant.court_service.utils;

public class FeignConstants {
    public static final String PATH_NOTIFY_CLIENT = "/order/notify";
    public static final String PATH_GET_USER_PHONE = "/user/phone/{userId}";
    public static final String USER_ID = "userId";

    private FeignConstants() {
        throw new UnsupportedOperationException(Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final int BAD_REQUEST_CODE = 400;
    public static final int NOT_FOUND_CODE = 404;

    public static final String BAD_REQUEST = "Bad Request";
    public static final String NOT_FOUND = "Not Found";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error from remote service";

    public static final String FEIGN_MESSAGING_NAME = "MESSAGING-API";
    public static final String PATH_MESSAGING_URL = "${messaging.base-url}";

    public static final String FEIGN_USER_NAME = "USER-API";
    public static final String FEIGN_USER_PATH = "${user.base-url}";
}
