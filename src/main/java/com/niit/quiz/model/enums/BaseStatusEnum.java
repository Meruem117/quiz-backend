package com.niit.quiz.model.enums;

public enum BaseStatusEnum {
    NOT_START(0),
    START(1),
    END(2);

    private final int value;

    BaseStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
