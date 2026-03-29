package com.simple.sample.controller;

import com.simple.sample.job.DynamicCronJob;
import com.simple.sample.job.DynamicTimedTask;
import com.simple.sample.util.http.NewResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kiturone
 * @date: 2021/7/24 13:39
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/dynamic/job")
public class DynamicJobController {

    @Autowired
    private DynamicCronJob dynamicCronJob;
    @Autowired
    private DynamicTimedTask dynamicTimedTask;

    @GetMapping(value = "/execute")
    public NewResponseModel<?> execute(@RequestParam(value = "cron", defaultValue = "0/10 * * * * ?") String cron) {
        log.info("修改任务执行时间，cron={}", cron);
        dynamicCronJob.setTaskCron(cron);
        return NewResponseModel.Success();
    }

    @GetMapping(value = "/execute/task")
    public NewResponseModel<?> executeTask(@RequestParam(value = "cron", defaultValue = "0/10 * * * * ?") String cron) {
        log.info("修改执行任务，并执行时间，cron={}", cron);
        dynamicTimedTask.startCron(() -> {
                    log.info("模拟执行任务，cron={}", cron);
                },
                cron);
        return NewResponseModel.Success();
    }
}
