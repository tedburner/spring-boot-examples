package com.example.SpringBoot;

import com.example.SpringBoot.dto.DO.GoodDO;

import java.time.LocalDateTime;

/**
 * @author lingjun.jlj
 * @date 2018-01-04
 **/
public class LombokTest {

    public static void main(String [] args){
        GoodDO goodDO = new GoodDO();
        goodDO.setId(1L);
        goodDO.setName("衣服");
        goodDO.setCreateTime(LocalDateTime.now());
        goodDO.setUpdateTime(LocalDateTime.now());
        System.out.println(goodDO);
    }
}
