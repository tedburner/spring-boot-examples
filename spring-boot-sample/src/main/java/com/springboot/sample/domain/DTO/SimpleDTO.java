package com.springboot.sample.domain.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author kiturone
 * @data 2018/4/14
 */
@Data
public class SimpleDTO {
    private Integer id;
    @NotNull(message = "name 不能为null")
    private String name;
    private String phone;
    private String birth;
}
