package com.niit.quiz.base.request;

import lombok.Data;

@Data
public class ResultAttendRequest {
    private int scheduleId;
    private int participantId;
    private int isTeam;
}
