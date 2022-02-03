package com.niit.quiz.model.enums;

public enum QuizWinnerIsTeamEnum {
    TEAM(1),
    USER(0);

    private final int value;

    QuizWinnerIsTeamEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
