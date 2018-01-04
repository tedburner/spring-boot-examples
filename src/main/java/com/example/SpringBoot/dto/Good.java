package com.example.SpringBoot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author lingjun.jlj
 * @create 2017-10-11
 **/
@Getter
@Setter
@ToString
public class Good {

    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
