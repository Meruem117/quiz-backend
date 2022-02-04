package com.niit.quiz.model.enums;

public enum IsTeamEnum {
    TEAM(1),
    USER(0);

    private final int value;

    IsTeamEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
