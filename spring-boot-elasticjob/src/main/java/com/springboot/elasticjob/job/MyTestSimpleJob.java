package com.springboot.elasticjob.job;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: lingjun.jlj
 * @date: 2018-12-05 15:28
 * @description: 测试定时器
 */
@Service
public class MyTestSimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("定时输出：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
