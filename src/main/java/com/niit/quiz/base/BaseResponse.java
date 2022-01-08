package com.niit.quiz.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer code;
    private T data;
    private String message;
}
