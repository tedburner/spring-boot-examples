package com.sb.cqrs.service.domain;

import java.time.LocalDateTime;

/**
 * @author: lingjun.jlj
 * @date: 2019/4/9 11:40
 * @description:
 */
public class UserDO {

    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String card;
    private String phone;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
