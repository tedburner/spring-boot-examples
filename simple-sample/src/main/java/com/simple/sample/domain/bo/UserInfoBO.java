package com.simple.sample.domain.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: kiturone
 * @date: 2019/9/20 13:50
 * @description:
 */
@Data
public class UserInfoBO {

    private String username;

    private String password;

    private Integer age;

    private String card;

    private String phone;

    private BigDecimal amount;
}
