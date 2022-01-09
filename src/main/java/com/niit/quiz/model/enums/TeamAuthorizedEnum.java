package com.niit.quiz.model.enums;

public enum TeamAuthorizedEnum {
    AUTHORIZED(1, "authorized"),
    UNAUTHORIZED(0, "unauthorized");

    private final int value;
    private final String text;

    TeamAuthorizedEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
