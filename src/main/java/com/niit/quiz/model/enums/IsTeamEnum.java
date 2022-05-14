package com.niit.quiz.model.enums;

import java.util.Objects;

public enum IsTeamEnum {
    TEAM(1),
    USER(0);

    private final Integer value;

    IsTeamEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Boolean include(Integer value) {
        for (IsTeamEnum isTeamEnum : IsTeamEnum.values()) {
            if (Objects.equals(isTeamEnum.getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
