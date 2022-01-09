package com.niit.quiz.base.exception;

public class BaseException extends RuntimeException {
    private final int code;
    private final String message;
    private String description;

    public BaseException(int code, String message, String description) {
        this(code, message);
        this.description = description;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.description = "";
    }

    public BaseException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }

    public BaseException(ErrorCodeEnum errorCodeEnum, String description) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getMessage(), description);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
