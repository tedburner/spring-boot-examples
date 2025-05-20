package com.springboot.sample.domain.DO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kiturone
 * @data 2018/4/2
 */
@Data
public class UserDO {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String card;
    private String phone;
    private Long provinceId;
    private Long cityId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
