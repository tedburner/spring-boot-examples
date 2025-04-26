package com.simple.sample;

import com.springboot.sample.starter.annotation.EnableSampleServer;
import com.springboot.sample.starter.service.MonitorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author: lingjun.jlj
 * @description: spring boot 简单项目实例
 */
@EnableAsync
@EnableScheduling
@EnableSampleServer
@EnableJpaAuditing
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class
SimpleSampleApplication implements CommandLineRunner {

    private final MonitorService monitorService;

    public SimpleSampleApplication(MonitorService monitorService) {
        this.monitorService = monitorService;
    }


    public static void main(String[] args) {
        SpringApplication.run(SimpleSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        monitorService.subscribe("abc", data -> System.out.println("receive data:" + data));
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("taskExecutor-test-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
