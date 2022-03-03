package com.springboot.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2022/3/3 21:49
 * @description:
 */
@Data
@TableName(value = "user")
public class UserInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String password;

    private String card;

    private String phone;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
