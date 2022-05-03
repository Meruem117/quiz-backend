package com.niit.quiz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("admin")
public class Admin implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 107204050360901797L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * email
     */
    @TableField("email")
    private String email;
    /**
     * name
     */
    @TableField("name")
    private String name;
    /**
     * password
     */
    @TableField("password")
    private String password;
    /**
     * create time
     */
    @TableField("create_time")
    private String createTime;
}
