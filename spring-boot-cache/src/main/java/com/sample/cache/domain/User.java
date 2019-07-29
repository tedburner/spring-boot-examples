package com.sample.cache.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Arthas
 * @date: 2018-12-26 16:56
 * @description:
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private Integer age;

    private String phone;

    private String card;

    private Integer status;

    private LocalDateTime createTime;


    private LocalDateTime updateTime;
}
