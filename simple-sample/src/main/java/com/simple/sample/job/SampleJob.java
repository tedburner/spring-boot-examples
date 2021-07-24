package com.simple.sample.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author: lingjun.jlj
 * @date: 2021/7/18 18:56
 * @description: 定时任务
 */
@Slf4j
//@Component
public class SampleJob {

    @Scheduled(cron = "* * * * * ?")
    public void printLog() {
        long time = System.currentTimeMillis();
        log.info("当前时间：{}", time);
    }
}
