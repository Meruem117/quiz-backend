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
     * question count, max 30
     */
    @TableField("count")
    private Integer count;
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
     * how many minutes a round last
     */
    @TableField("length")
    private Integer length;
    /**
     * status: 0-not start, 1-start, 2-end
     */
    @TableField("status")
    private Integer status;
}
