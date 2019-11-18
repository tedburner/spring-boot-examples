package com.springboot.sample.service.common.impl;

import com.kit.common.util.common.ListUtils;
import com.springboot.sample.domain.DO.UserDO;
import com.springboot.sample.persist.UserMapper;
import com.springboot.sample.service.common.UserService;
import com.kit.common.util.thread.task.TaskFunction;
import com.kit.common.util.thread.task.TaskRequest;
import com.kit.common.util.thread.threadpool.FastThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucifer
 * @data 2018/4/2
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private FastThreadPool fastThreadPool;

    /**
     * 线程使用例子
     */
    @Override
    public List<UserDO> findUser() {
        List<TaskFunction> taskFunctions = new ArrayList<>();
        taskFunctions.add(() -> userMapper.selectUser());
        List result = fastThreadPool.execute(new TaskRequest(taskFunctions, false));
        return (List<UserDO>) result.get(0);

    }

    @Override
    public void addUser(UserDO userDO) {
        userMapper.addUser(userDO);
    }

    @Override
    public void update(UserDO userDO) {
        userMapper.update(userDO);
    }

    @Override
    public UserDO findById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update() {

        try {
            UserDO userDO = userMapper.selectById(1L);
            System.out.println("修改前: " + userDO);
            userDO.setName("孙肖宇");
            userMapper.update(userDO);

            Thread.sleep(1000L);
            UserDO updated = userMapper.selectById(1L);
            System.out.println("修改后： " + updated);

            Thread.sleep(2000L);

        } catch (InterruptedException e) {

        }

    }

    @Override
    public UserDO findUserByName(String name) {

        return userMapper.selectByName(name).orElseThrow(() -> new RuntimeException("未找到该用户信息"));
    }

    @Override
    public List<UserDO> findUserLikeName(String name) {
        //返回的数组不是null，而是一个 size=0 的数组
        List<UserDO> userList = userMapper.selectLikeName(name);
        if (userList == null) {
            log.info("查询的数组为 null");
        }
        if (userList.size() == 0) {
            log.info("查询的数组为空");
        }
        if (ListUtils.isEmpty(userList)) {
            log.info("查询的数组为空");
        }
        return userMapper.selectLikeName(name);
    }

    @Override
    public void save(UserDO userDO) {
        userMapper.replaceUser(userDO);
    }
}
