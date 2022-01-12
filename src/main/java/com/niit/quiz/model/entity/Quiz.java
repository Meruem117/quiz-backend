package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
     * question list, max 30 questions
     */
    @TableField("question")
    private String question;
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
