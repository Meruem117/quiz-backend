package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("topic")
public class Topic implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -2806074660382555301L;

    /**
     * topic id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * topic name
     */
    @TableField("topic")
    private String topic;
    /**
     * topic description
     */
    @TableField("description")
    private String description;
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
