package com.springboot.job.elasticjob;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2018-12-05 15:28
 * @description: elastic job 测试定时器
 */
@Slf4j
@Component
public class MyTestSimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("elastic job execute tasK:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
