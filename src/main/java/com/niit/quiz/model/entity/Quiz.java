package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("quiz")
public class Quiz implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 6418669843021485385L;

    /**
     * quiz id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * quiz name
     */
    @TableField("quiz")
    private String quiz;
    /**
     * topic name
     */
    @TableField("topic")
    private String topic;
    /**
     * total rounds
     */
    @TableField("round")
    private Integer round;
    /**
     * creator name
     */
    @TableField("creator")
    private String creator;
    /**
     * creator id
     */
    @TableField("creator_id")
    private Integer creatorId;
    /**
     * question list
     */
    @TableField("question")
    private String question;
    /**
     * participant list
     */
    @TableField("participant")
    private String participant;
    /**
     * quiz start time
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * quiz end time
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * is start or not: 1-start, 0-not start
     */
    @TableField("is_start")
    private Integer isStart;
    /**
     * is end or not: 1-end, 0-not end
     */
    @TableField("is_end")
    private Integer isEnd;
    /**
     * winner name
     */
    @TableField("winner")
    private String winner;
    /**
     * winner id
     */
    @TableField("winner_id")
    private Integer winnerId;
    /**
     * winner is team or individual: 1-team, 0-individual
     */
    @TableField("is_team")
    private Integer isTeam;
}
