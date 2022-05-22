package com.niit.quiz.model.enums;

public enum QuitEnum {
    QUIT(1),
    NOT_QUIT(0);

    private final Integer value;

    QuitEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
