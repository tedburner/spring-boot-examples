package com.springboot.elasticjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author : Lucifer
 * @description: ElasticJob 分布式任务
 *
 * 推荐crontab 工具 http://cron.qqe2.com/
 * */
@SpringBootApplication
@ImportResource("classpath:elasticjob.xml")
public class ElasticJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticJobApplication.class, args);
	}
}
