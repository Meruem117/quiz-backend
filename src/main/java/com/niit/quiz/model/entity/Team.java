package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

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
     * description of team
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
