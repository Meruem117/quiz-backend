package com.niit.quiz.model.enums;

public enum StatusEnum {
    NOT_START(0),
    START(1),
    END(2);

    private final Integer value;

    StatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
