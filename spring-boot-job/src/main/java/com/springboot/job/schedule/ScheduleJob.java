package com.springboot.job.schedule;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lingjun.jlj
 * @date: 2020/8/15 15:39
 * @description: spring schedule 定时任务
 */
@Slf4j
@Component
public class ScheduleJob {

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    @Scheduled(cron = "0/5 * * * * ?")
    public void task() {
        log.info("Spring Schedule execute tasK:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    public void threadPoolTask() {
        log.info("线程池开始执行任务");
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        AtomicInteger number = new AtomicInteger(0);
        Semaphore semaphore = new Semaphore(10);
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    number.incrementAndGet();
                    try {
                        semaphore.acquire();
                        if (number.get() == 1) {
                            Thread.sleep(600000);
                        } else {
                            Thread.sleep(50000);
                        }
                        log.info("do something ... ");
                    } catch (Exception e) {
                        log.info("do something ... ");
                    } finally {
                        semaphore.release();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await(1, TimeUnit.MINUTES);
            executor.shutdownNow();
        } catch (Exception e) {

        }
        log.info("任务执行结束");
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void threadPoolMonitor() {
        System.out.println("=====================================thread-pool-info: =====================================");
        System.out.println("CorePoolSize:" + executor.getCorePoolSize());
        System.out.println("MaxPoolSize: " + executor.getMaximumPoolSize());
        System.out.println("PoolSize:" + executor.getPoolSize());
        System.out.println("ActiveCount:" + executor.getActiveCount());
        System.out.println("KeepAliveTime:" + executor.getKeepAliveTime(TimeUnit.SECONDS));
        System.out.println("QueueSize:" + executor.getQueue().size());
    }
}
