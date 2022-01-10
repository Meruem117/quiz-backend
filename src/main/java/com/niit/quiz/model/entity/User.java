package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
     * role: 2-root, 1-admin, 0-user
     */
    @TableField("role")
    private Integer role;
    /**
     * joined teams
     */
    @TableField("team")
    private String team;
    /**
     * create time
     */
    @TableField("create_time")
    private Date createTime;
}
