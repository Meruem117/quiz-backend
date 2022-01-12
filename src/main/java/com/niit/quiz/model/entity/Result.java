package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("result")
public class Result implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -6608152894336762879L;

    /**
     * result id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * quiz id
     */
    @TableField("quiz_id")
    private Integer quizId;
    /**
     * quiz name
     */
    @TableField("quiz_name")
    private String quizName;
    /**
     * current round
     */
    @TableField("round")
    private Integer round;
    /**
     * schedule id
     */
    @TableField("schedule_id")
    private Integer scheduleId;
    /**
     * participant id
     */
    @TableField("participant_id")
    private Integer participantId;
    /**
     * participant name
     */
    @TableField("participant_name")
    private Integer participantName;
    /**
     * is team or individual: 1-team, 0-individual
     */
    @TableField("is_team")
    private Integer isTeam;
    /**
     * mark
     */
    @TableField("mark")
    private Integer mark;
    /**
     * error question list, max 30 questions
     */
    @TableField("error_list")
    private String errorList;
    /**
     * is out or not: 1-out, 0-not out
     */
    @TableField("is_out")
    private Integer isOut;
}
