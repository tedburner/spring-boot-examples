package com.example.jooq.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author: kiturone
 * @date: 2022/3/3 21:49
 * @description:
 */
@Data
public class UserInfo {

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
