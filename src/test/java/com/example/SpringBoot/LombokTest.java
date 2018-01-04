package com.example.SpringBoot;

import com.example.SpringBoot.dto.Good;

import java.time.LocalDateTime;

/**
 * @author lingjun.jlj
 * @date 2018-01-04
 **/
public class LombokTest {

    public static void main(String [] args){
        Good good = new Good();
        good.setId(1L);
        good.setName("衣服");
        good.setCreateTime(LocalDateTime.now());
        good.setUpdateTime(LocalDateTime.now());
        System.out.println(good);
    }
}
