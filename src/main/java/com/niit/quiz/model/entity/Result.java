package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("result")
public class Result implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -6608152894336762879L;
}
