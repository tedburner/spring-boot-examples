package com.springboot.job.xxljob.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * xxl-job 任务处理器示例
 *
 * @author kiturone
 * @date 2026/03/29
 */
@Slf4j
@Component
public class SampleXxlJob {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 简单任务示例
     */
    @XxlJob("simpleJobHandler")
    public void simpleJobHandler() {
        String now = LocalDateTime.now().format(FORMATTER);
        log.info("XXL-JOB simpleJobHandler 执行时间: {}", now);
        XxlJobHelper.log("XXL-JOB simpleJobHandler 执行时间: {}", now);
    }

    /**
     * 分片广播任务示例
     * 使用分片参数处理大数据量任务
     */
    @XxlJob("shardingJobHandler")
    public void shardingJobHandler() {
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        String now = LocalDateTime.now().format(FORMATTER);
        log.info("XXL-JOB shardingJobHandler 执行时间: {}, 分片参数: 当前分片={}, 总分片数={}",
                now, shardIndex, shardTotal);

        // 业务逻辑：根据分片参数处理数据
        // 例如：处理 id % shardTotal == shardIndex 的数据
        XxlJobHelper.log("分片任务执行, 当前分片={}, 总分片数={}", shardIndex, shardTotal);

        // 返回执行结果
        XxlJobHelper.handleSuccess("分片任务执行成功");
    }

    /**
     * 参数任务示例
     * 接收任务参数并处理
     */
    @XxlJob("paramJobHandler")
    public void paramJobHandler() {
        // 获取任务参数
        String param = XxlJobHelper.getJobParam();
        String now = LocalDateTime.now().format(FORMATTER);

        log.info("XXL-JOB paramJobHandler 执行时间: {}, 参数: {}", now, param);
        XxlJobHelper.log("任务参数: {}", param);

        // 根据 param 处理不同业务逻辑
        if ("mode1".equals(param)) {
            XxlJobHelper.log("执行模式1业务逻辑");
        } else if ("mode2".equals(param)) {
            XxlJobHelper.log("执行模式2业务逻辑");
        }

        XxlJobHelper.handleSuccess("任务执行成功");
    }

    /**
     * 子任务示例
     * 执行完成后触发子任务
     */
    @XxlJob("childTriggerJobHandler")
    public void childTriggerJobHandler() {
        String now = LocalDateTime.now().format(FORMATTER);
        log.info("XXL-JOB childTriggerJobHandler 执行时间: {}", now);

        // 执行业务逻辑...

        // 触发子任务（可选）
        // XxlJobHelper.handleSuccess("任务执行成功，触发子任务: childJobHandler");
    }
}