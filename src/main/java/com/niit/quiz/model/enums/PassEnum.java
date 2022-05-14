package com.niit.quiz.model.enums;

public enum PassEnum {
    PENDING("0"),
    PASS("1"),
    NOT_PASS("2");

    private final String value;

    PassEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
