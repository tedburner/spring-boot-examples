package com.sample.springboot.persist;

import com.sample.springboot.domain.DO.UserDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

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

    /**
     * 查询用户信息
     *
     * @param name
     * @return 返回Optional类型用户信息
     */
//    @Select("select * from user where name = #{name} limit 1")
    Optional<UserDO> selectByName(String name);

}
