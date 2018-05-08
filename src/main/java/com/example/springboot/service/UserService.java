package com.example.springboot.service;

import com.example.springboot.model.DO.UserDO;

import java.util.List;

/**
 * @author lingjun.jlj
 * @data 2018/4/2
 */
public interface UserService {
    List<UserDO> findUser();
}
