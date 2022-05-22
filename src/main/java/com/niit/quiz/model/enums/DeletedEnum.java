package com.niit.quiz.model.enums;

public enum DeletedEnum {
    DELETED(1),
    NOT_DELETED(0);

    private final Integer value;

    DeletedEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
