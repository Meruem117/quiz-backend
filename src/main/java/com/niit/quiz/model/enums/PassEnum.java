package com.niit.quiz.model.enums;

import java.util.Objects;

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

    public static Boolean include(String value) {
        for (PassEnum passEnum : PassEnum.values()) {
            if (Objects.equals(passEnum.getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
