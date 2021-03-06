package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    private String participantName;
    /**
     * is team or individual: 1-team, 0-individual
     */
    @TableField("is_team")
    private Integer isTeam;
    /**
     * status: 0-not start, 1-start, 2-end
     */
    @TableField("status")
    private Integer status;
    /**
     * isTake: 0-not take, 1-take
     */
    @TableField("is_take")
    private Integer isTake;
    /**
     * correct answer count
     */
    @TableField("correct")
    private Integer correct;
    /**
     * correct rate
     */
    @TableField("correct_rate")
    private Integer correctRate;
    /**
     * answer list, max 30
     */
    @TableField("answers")
    private String answers;
    /**
     * error question list, max 30
     */
    @TableField("errors")
    private String errors;
    /**
     * is out or not: 0-pending, 1-not out, 2-out
     */
    @TableField("is_out")
    private String isOut;
    /**
     * take time
     */
    @TableField("take_time")
    private String takeTime;
    /**
     * create time
     */
    @TableField("create_time")
    private String createTime;
    /**
     * deleted - logical delete
     */
    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT, select = false)
    private Integer deleted;
}
