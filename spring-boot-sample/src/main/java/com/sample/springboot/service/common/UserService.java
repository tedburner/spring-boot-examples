package com.sample.springboot.service.common;

import com.sample.springboot.domain.DO.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lucifer
 * @data 2018/4/2
 */
public interface UserService {

    List<UserDO> findUser();

    void addUser(UserDO userDO);

    void update(UserDO userDO);

    /**
     * 根据用户id查询
     * */
    UserDO findById( Long id);
}
