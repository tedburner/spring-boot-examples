package com.simple.sample.job;

import com.simple.sample.constant.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: kiturone
 * @date: 2021/7/24 12:05
 * @description: 动态定时任务
 */
@Slf4j
@Component
public class DynamicCronJob implements SchedulingConfigurer {

    /**
     * 默认定时任务执行时间
     */
    private String taskCron = GlobalConstants.TASK_DEFAULT_CRON;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
            long time = System.currentTimeMillis();
//            log.info("执行任务，当前时间：{}", time);
        }, triggerContext -> {
            // 刷新cron时间
            CronTrigger cronTrigger = new CronTrigger(taskCron);
            Date nextExecuteTime = cronTrigger.nextExecutionTime(triggerContext);
            return nextExecuteTime;
        });
    }

    /**
     * 修改默认的定时任务时间
     */
    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }
}
