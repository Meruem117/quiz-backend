package com.niit.quiz.model.enums;

public enum UserRoleEnum {
    ROOT(2, "root"),
    ADMIN(1, "admin"),
    USER(0, "user");

    private final int value;
    private final String text;

    UserRoleEnum(int value, String text) {
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
