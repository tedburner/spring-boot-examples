package com.sample.springboot.service.common.impl;

import com.sample.springboot.domain.DO.UserDO;
import com.sample.springboot.persist.UserMapper;
import com.sample.springboot.service.common.UserService;
import com.kit.common.util.thread.task.TaskFunction;
import com.kit.common.util.thread.task.TaskRequest;
import com.kit.common.util.thread.threadpool.FastThreadPool;
import org.apache.catalina.User;
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
}
