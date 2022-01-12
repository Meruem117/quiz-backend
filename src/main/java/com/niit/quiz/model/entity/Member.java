package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("member")
public class Member implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -5973443410802022179L;

    /**
     * member id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * team id
     */
    @TableField("team_id")
    private Integer teamId;
    /**
     * team name
     */
    @TableField("team_name")
    private String teamName;
    /**
     * user id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * username
     */
    @TableField("user_name")
    private String userName;
    /**
     * join time
     */
    @TableField("join_time")
    private String joinTime;
}
