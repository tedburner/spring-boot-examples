package com.simple.sample.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

/**
 * @author: lingjun.jlj
 * @date: 2021/7/24 13:31
 * @description:
 */
@Slf4j
@Component
public class DynamicTimedTask {

    private static boolean start = false;

    /**
     * 接受任务的返回结果
     */
    private ScheduledFuture<?> future;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 启动定时任务
     *
     * @param task 执行任务
     * @param cron 执行频率
     * @return
     */
    public boolean startCron(Runnable task, String cron) {
        // 停止上一次定时任务
        stopCron();
        //从数据库动态获取执行周期
        future = threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
        if (future != null) {
            log.info("start cron task success");
            start = true;
            return true;
        }
        return false;
    }

    /**
     * 停止定时任务
     *
     * @return
     */
    public boolean stopCron() {
        if (!start) {
            log.info("没有上一次任务");
            return false;
        }
        boolean cancel = future.cancel(true);
        if (cancel) {
            log.info("start cron task success");
            start = false;
            return true;
        }
        return false;
    }
}
