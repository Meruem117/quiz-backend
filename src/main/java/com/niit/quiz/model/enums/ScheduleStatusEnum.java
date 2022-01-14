package com.niit.quiz.model.enums;

public enum ScheduleStatusEnum {
    START(1, "quiz round has started"),
    NOT_START(0, "quiz round has not started"),
    END(1, "quiz round has ended"),
    NOT_END(0, "quiz round has not ended");

    private final int value;
    private final String text;

    ScheduleStatusEnum(int value, String text) {
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
