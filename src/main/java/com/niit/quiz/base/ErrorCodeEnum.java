package com.niit.quiz.base;

public enum ErrorCodeEnum {
    /**
     * request param error
     */
    REQUEST_PARAMS_ERROR(400, "request param error"),

    /**
     * system error
     */
    SYSTEM_ERROR(500, "system error");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
