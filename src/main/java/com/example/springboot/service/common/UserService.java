package com.example.springboot.service.common;

import com.example.springboot.model.DO.UserDO;

import java.util.List;

/**
 * @author lingjun.jlj
 * @data 2018/4/2
 */
public interface UserService {

    List<UserDO> findUser();

    void addUser(UserDO userDO);

    void update(UserDO userDO);
}
