package com.sample.springboot.persist;

import com.sample.springboot.domain.DO.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lucifer
 * @create 2017-09-22
 **/

public interface UserMapper {

    void addUser(UserDO user);

    void update(UserDO userDO);

    List<UserDO> selectUser();

    /**
     * 根据用户表id查询用户信息
     *
     * @param id
     * @return
     */
    UserDO selectById(@Param("id") Long id);

}
