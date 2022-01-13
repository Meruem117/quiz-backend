package com.niit.quiz.model.enums;

public enum QuizWinnerIsTeamEnum {
    TEAM(1, "team"),
    USER(0, "user");

    private final int value;
    private final String text;

    QuizWinnerIsTeamEnum(int value, String text) {
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
