package com.springboot.shardingsphere.config;

import com.alibaba.druid.pool.DruidDataSource;
import groovy.util.logging.Slf4j;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.core.keygen.KeyGenerator;
import io.shardingsphere.core.rule.ShardingRule;
import io.shardingsphere.core.rule.TableRule;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Arthas
 * @date: 2018-12-28 11:37
 * @description: 分片设置
 */
@Slf4j
@Configuration
public class DataSourceConfig {


}
