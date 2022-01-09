package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("team")
public class Team implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 7040621777895623052L;

    /**
     * team id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * team name
     */
    @TableField("name")
    private String name;
    /**
     * team leader
     */
    @TableField("leader")
    private String leader;
    /**
     * leader id
     */
    @TableField("leader_id")
    private Integer leaderId;
    /**
     * create time
     */
    @TableField("create_time")
    private Date createTime;
}