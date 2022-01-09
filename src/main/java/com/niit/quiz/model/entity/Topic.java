package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
}
