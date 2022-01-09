package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("question")
public class Question implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 2588782277763305335L;

    /**
     * question id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * question context
     */
    @TableField("question")
    private String question;
    /**
     * question topic
     */
    @TableField("topic")
    private String topic;
    /**
     * question type 1-single choice 0-multiple choice
     */
    @TableField("type")
    private Integer type;
    /**
     * option a
     */
    @TableField("option_a")
    private String optionA;
    /**
     * option b
     */
    @TableField("option_b")
    private String optionB;
    /**
     * option c
     */
    @TableField("option_c")
    private String optionC;
    /**
     * option d
     */
    @TableField("option_d")
    private String optionD;
    /**
     * correct answer
     */
    @TableField("answer")
    private String answer;
    /**
     * score
     */
    @TableField("score")
    private Integer score;
    /**
     * create time
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * update time
     */
    @TableField("update_time")
    private Date updateTime;
}
