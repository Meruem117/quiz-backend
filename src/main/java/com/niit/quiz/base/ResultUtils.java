package com.niit.quiz.base;

public class ResultUtils {
    /**
     * succeed
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "success");
    }

    /**
     * error
     */
    public static <T> BaseResponse<T> error(int code, String errorMessage) {
        return new BaseResponse<>(code, null, errorMessage);
    }
}
