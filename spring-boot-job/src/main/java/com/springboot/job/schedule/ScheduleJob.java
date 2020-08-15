package com.springboot.job.schedule;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2020/8/15 15:39
 * @description: spring schedule 定时任务
 */
@Slf4j
@Component
public class ScheduleJob {

    @Scheduled(cron = "0/5 * * * * ?")
    public void task() {
        log.info("Spring Schedule execute tasK:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
