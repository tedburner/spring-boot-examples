package com.springboot.datajpa.service;

import com.springboot.datajpa.domain.User;

import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2018-12-26 16:55
 * @description:
 */
public interface UserService {


    /**
     * 插入用户信息
     *
     * @param user
     */
    void save(User user);

    /**
     * 插入用户信息
     *
     * @param users
     */
    void save(List<User> users);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findUserAll();

    /**
     * 根据用户id查询用户信息
     * */
    User findUserById(Long id);

    /**
     * 获取数据流
     *
     * @return
     */
    List<User> findUserStream();

    /**
     * 修改用户名字
     *
     * @param id
     * @param name
     */
    void updateUserName(Long id, String name);
}
