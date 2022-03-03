package com.springboot.mybatisplus.service;

import com.springboot.mybatisplus.domain.UserInfo;

import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2022/3/3 22:04
 * @description:
 */
public interface UserInfoService {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 根据名字查询信息
     *
     * @param name
     * @return
     */
    List<UserInfo> findByName(String name);

    /**
     * 根据ID查询用户信息
     *
     * @param id
     * @return
     */
    UserInfo findById(Long id);

    /**
     * 插入用户信息
     *
     * @param name
     * @param card
     * @param phone
     * @param age
     */
    void save(String name, String card, String phone, Integer age);
}
