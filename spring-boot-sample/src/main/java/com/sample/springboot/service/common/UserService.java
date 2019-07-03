package com.sample.springboot.service.common;

import com.sample.springboot.domain.DO.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lucifer
 * @data 2018/4/2
 */
public interface UserService {


    /**
     * 查询用户信息
     */
    List<UserDO> findUser();


    /**
     * 添加用户信息
     */
    void addUser(UserDO userDO);

    /**
     * 修改用户信息
     */
    void update(UserDO userDO);

    /**
     * 根据用户id查询
     */
    UserDO findById(Long id);

    /**
     * 测试同一事务先修改，在查询问题
     */
    void update();

    /**
     * 根据名字查询用户信息
     *
     * @param name
     * @return 用户信息
     */
    UserDO findUserByName(String name);
}
