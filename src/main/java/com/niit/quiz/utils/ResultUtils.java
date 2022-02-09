package com.niit.quiz.utils;

import com.niit.quiz.base.response.BaseResponse;

public class ResultUtils {
    /**
     * success
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
