package com.example.fluent.mybatis.service;


import com.example.fluent.mybatis.entity.UserEntity;

/**
 * @author: kiturone
 * @date: 2022/3/22 22:23
 * @description:
 */
public interface UserService {

    UserEntity findById(Long id);

    UserEntity findByName(String name);

    void save();
}
