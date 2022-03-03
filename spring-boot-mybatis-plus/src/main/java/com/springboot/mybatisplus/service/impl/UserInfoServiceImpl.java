package com.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.mybatisplus.domain.UserInfo;
import com.springboot.mybatisplus.mapper.UserInfoMapper;
import com.springboot.mybatisplus.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2022/3/3 22:04
 * @description:
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<UserInfo> findByName(String name) {
        return userInfoMapper.selectList(new QueryWrapper<UserInfo>()
                .lambda().eq(UserInfo::getName, name));
    }

    @Override
    public UserInfo findById(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public void save(String name, String card, String phone, Integer age) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setPassword("1111111111");
        userInfo.setAge(age);
        userInfo.setCard(card);
        userInfo.setPhone(phone);
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        userInfoMapper.insert(userInfo);
    }
}
