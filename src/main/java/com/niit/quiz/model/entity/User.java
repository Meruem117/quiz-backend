package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 7279380465148323628L;

    /**
     * user id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * email
     */
    @TableField("email")
    private String email;
    /**
     * username
     */
    @TableField("name")
    private String name;
    /**
     * password
     */
    @TableField("password")
    private String password;
    /**
     * gender
     */
    @TableField("gender")
    private Integer gender;
    /**
     * location
     */
    @TableField("location")
    private String location;
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
