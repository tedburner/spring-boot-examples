package com.example.springboot.utils.thread.task;


import com.google.common.util.concurrent.FutureCallback;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lucifer
 * @create 2017-10-23
 **/

public class TaskRequest implements Serializable {

    /**
     * 任务数
     */
    private int taskCount;

    /**
     * 任务列表
     */
    private List<TaskFunction> taskList;

    /**
     * 回调函数
     */
    private FutureCallback callback;

    /**
     * 失败策略，是否忽略error
     */
    private boolean ignoreError;

    public TaskRequest(List<TaskFunction> taskList) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
        this.ignoreError = true;
    }

    public TaskRequest(List<TaskFunction> taskList, FutureCallback callback) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
        this.callback = callback;
        this.ignoreError = true;
    }

    public TaskRequest(List<TaskFunction> taskList, Boolean ignoreError) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
        this.ignoreError = ignoreError;
    }

    public TaskRequest(List<TaskFunction> taskList, Boolean ignoreError, FutureCallback callback) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
        this.ignoreError = ignoreError;
        this.callback = callback;
    }

    public int getTaskCount() {
        return taskCount;
    }


    public List<TaskFunction> getTaskList() {
        return taskList;
    }

    public FutureCallback getCallback() {
        return callback;
    }

    public Boolean getIgnoreError() {
        return ignoreError;
    }
}