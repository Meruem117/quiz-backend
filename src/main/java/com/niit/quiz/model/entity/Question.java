package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

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
     * uploader
     */
    @TableField("up")
    private String up;
    /**
     * uploader id
     */
    @TableField("up_id")
    private Integer upId;
    /**
     * question topic
     */
    @TableField("topic")
    private String topic;
    /**
     * question type: 1-single choice, 0-multiple choice
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
     * is passed: 0 - pending, 1 - pass, 2 - not pass
     */
    @TableField(value = "pass", fill = FieldFill.INSERT)
    private String pass;
    /**
     * create time
     */
    @TableField("create_time")
    private String createTime;
    /**
     * update time
     */
    @TableField("update_time")
    private String updateTime;
    /**
     * deleted - logical delete
     */
    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT, select = false)
    private Integer deleted;
}
