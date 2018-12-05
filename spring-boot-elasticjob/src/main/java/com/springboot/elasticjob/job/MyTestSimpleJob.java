package com.springboot.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: Lucifer
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
