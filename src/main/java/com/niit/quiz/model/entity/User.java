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
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 用户名
     */
    @TableField("name")
    private String name;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 角色 2-超级管理员 1-管理员 0-用户
     */
    @TableField("role")
    private Integer role;
    /**
     * 加入的组
     */
    @TableField("team")
    private String team;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
