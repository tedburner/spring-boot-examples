package com.springboot.mongodb.service;


import com.springboot.mongodb.domain.PageDTO;
import com.springboot.mongodb.domain.User;

import java.util.List;

/**
 * @author: kiturone
 * @Date: 2018/7/11 11:41
 * @Description:
 */
public interface UserMongoDBService {

    /**
     * 插入用户信息
     */
    void save(User user);

    /**
     * 根据用户ID更新信息
     */
    void update(User user);

    /**
     * 查找所有用户信息
     */
    List<User> findUserAll();

    /**
     * 根据用户名查询用户信息
     *
     * @param name
     */
    List<User> findUserByName(String name);

    /**
     * 根据用户名查询用户信息
     *
     * @param name
     */
    List<User> findUserByNameLike(String name);

    User findUserById(String id);

    /**
     * 分页查询
     *
     * @param pageDTO
     */
    List<User> findUserByPage(PageDTO pageDTO);
}
