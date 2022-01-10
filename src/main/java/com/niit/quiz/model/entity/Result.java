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
    private static final long serialVersionUID = -2834046975062006391L;

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
     * round
     */
    @TableField("round")
    private Integer round;
    /**
     * is the last round: 1-last round, 0-not last round
     */
    @TableField("is_end")
    private Integer isEnd;
    /**
     * participant id
     */
    @TableField("participant_id")
    private Integer participantId;
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
     * is out or not: 1-out, 0-not out
     */
    @TableField("is_out")
    private Integer isOut;
}
