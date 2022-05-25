package com.niit.quiz.model.enums;

import java.util.Objects;

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

    public static Boolean include(Integer value) {
        for (QuitEnum quitEnum : QuitEnum.values()) {
            if (Objects.equals(quitEnum.getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
