package com.postgres.sample.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/1 16:47
 * @version：1.0
 * @description:
 */
@Data
@Entity
@Table(name = "test_user")
public class UserDO {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String idCard;

    private Date createTime;

    private Date updateTime;
}
