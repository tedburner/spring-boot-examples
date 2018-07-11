package com.example.springboot.mongo.entity;

import lombok.Data;


import org.springframework.data.annotation.Id;
import java.io.Serializable;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/11 11:18
 * @Description:
 */
@Data
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String password;
}
