package com.niit.quiz.model.enums;

public enum IsTakeEnum {
    TAKE(1),
    NOT_TAKE(0);

    private final Integer value;

    IsTakeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
