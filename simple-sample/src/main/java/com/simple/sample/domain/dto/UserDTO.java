package com.simple.sample.domain.dto;

import com.simple.sample.aspect.annotation.FieldCase;
import lombok.Data;

/**
 * @author: lingjun.jlj
 * @date: 2020/4/2 20:25
 * @description:
 */
@Data
public class UserDTO {

    private Integer id;

    @FieldCase
    private String username;

    private String password;

    private String cardNo;
}
