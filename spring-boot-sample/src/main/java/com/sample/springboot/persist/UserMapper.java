package com.sample.springboot.persist;

import com.sample.springboot.domain.DO.UserDO;

import java.util.List;

/**
 * @author Lucifer
 * @create 2017-09-22
 **/

public interface UserMapper {

    void addUser(UserDO user);

    void update(UserDO userDO);

    List<UserDO> selectUser();

}
