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

    public static Boolean include(int value) {
        for (IsTeamEnum isTeamEnum : IsTeamEnum.values()) {
            if (isTeamEnum.getValue() == value) {
                return true;
            }
        }
        return false;
    }

}
