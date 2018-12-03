package com.sample.springboot.service.common;

import com.sample.springboot.domain.DO.UserDO;

import java.util.List;

/**
 * @author Lucifer
 * @data 2018/4/2
 */
public interface UserService {

    List<UserDO> findUser();

    void addUser(UserDO userDO);

    void update(UserDO userDO);
}
