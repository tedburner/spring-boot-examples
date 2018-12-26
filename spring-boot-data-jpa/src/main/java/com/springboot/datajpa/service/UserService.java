package com.springboot.datajpa.service;

import com.springboot.datajpa.domain.User;

import java.util.List;

/**
 * @author: Arthas
 * @date: 2018-12-26 16:55
 * @description:
 */
public interface UserService {


    /**
     * 插入用户信息
     */
    void save(User user);

    /**
     * 插入用户信息
     */
    void save(List<User> users);

    /**查询所有用户*/
    List<User> findUserAll();

    /**获取数据流*/
    List<User>findUserStream();
}
