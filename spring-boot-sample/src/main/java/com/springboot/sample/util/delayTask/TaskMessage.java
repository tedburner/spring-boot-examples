package com.springboot.sample.util.delayTask;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 16:41
 * @Description: 消息延迟任务
 */
public class TaskMessage implements Delayed {
    private String body;
    private long executeTime;
    private Function function;

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getExecuteTime() {
        return this.executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public Function getFunction() {
        return this.function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public TaskMessage(Long delayTime, String body, Function function) {
        this.body = body;
        this.function = function;
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    public int compareTo(Delayed delayed) {
        TaskMessage msg = (TaskMessage) delayed;
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - msg.getDelay(TimeUnit.MILLISECONDS));
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }
}
