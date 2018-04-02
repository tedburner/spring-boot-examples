package com.example.SpringBoot.persist;

import com.example.SpringBoot.dto.DO.UserDO;

import java.util.List;

/**
 * @author lingjun.jlj
 * @create 2017-09-22
 **/

public interface UserMapper {

    Long addUser(UserDO user);

    List<UserDO> selectUser();

}
