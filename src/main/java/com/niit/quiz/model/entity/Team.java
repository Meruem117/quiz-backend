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
     * 组id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 组名
     */
    @TableField("name")
    private String name;
    /**
     * 组长
     */
    @TableField("leader")
    private String leader;
    /**
     * 组长id
     */
    @TableField("leader_id")
    private Integer leaderId;
    /**
     * 组创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
