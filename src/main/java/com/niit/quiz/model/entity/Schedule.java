package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("schedule")
public class Schedule implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -2834046975062006391L;

    /**
     * schedule id
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
     * question list, max 30 questions
     */
    @TableField("question")
    private String question;
    /**
     * current round start time
     */
    @TableField("start_time")
    private String startTime;
    /**
     * current round end time
     */
    @TableField("end_time")
    private String endTime;
    /**
     * is the round start: 1-round start, 0-round not start
     */
    @TableField("is_start")
    private Integer isStart;
    /**
     * is the round end: 1-round end, 0-round not end
     */
    @TableField("is_end")
    private Integer isEnd;
}
