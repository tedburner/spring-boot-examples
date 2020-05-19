package com.springboot.webflux.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2019-05-16 22:45
 * @description:
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
