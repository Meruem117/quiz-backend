package com.niit.quiz.model.enums;

public enum IsOutEnum {
    PENDING("0"),
    NOT_OUT("1"),
    OUT("2");

    private final String value;

    IsOutEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
