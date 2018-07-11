package com.example.springboot.service.mongo;

import com.example.springboot.mongo.entity.User;

import java.util.List;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/11 11:41
 * @Description:
 */
public interface UserMongoDBService {

    List<User> findUserAll();
}
