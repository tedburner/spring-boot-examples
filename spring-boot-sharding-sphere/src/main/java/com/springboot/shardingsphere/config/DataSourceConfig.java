package com.springboot.shardingsphere.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.springboot.shardingsphere.sharding.DatabaseShardingAlgorithm;
import com.springboot.shardingsphere.sharding.TablePreciseShardingAlgorithm;
import com.springboot.shardingsphere.sharding.TableRangeShardingAlgorithm;
import com.zaxxer.hikari.HikariDataSource;
import groovy.util.logging.Slf4j;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.core.keygen.KeyGenerator;
import io.shardingsphere.core.rule.ShardingRule;
import io.shardingsphere.core.rule.TableRule;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: Arthas
 * @date: 2018-12-28 11:37
 * @description: 分片设置
 */
@Slf4j
@Configuration
public class DataSourceConfig {


    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        //添加两个数据源到map中
        dataSourceMap.put("db0", createDataSource("sharding-test"));
        dataSourceMap.put("db1", createDataSource("sharding-test"));

        // 设置默认db为db0，也就是为那些没有配置分库分表策略的指定的默认库
        // 如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "db0");

        // 设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
        // 0和1两个表是真实的表，t_order是个虚拟不存在的表，只是供使用。如查询所有数据就是select * from t_order就能查完0和1表的
        TableRule orderTableRule = TableRule.builder("t_order")
                .actualTables(Arrays.asList("t_order_0", "t_order_1"))
                .dataSourceRule(dataSourceRule)
                .build();

        // 具体分库分表策略，按什么规则来分
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuleDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuleTableShardingAlgorithm())).build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);

        return dataSource;
    }

    private static DataSource createDataSource(final String dataSourceName) {
        // 使用druid连接数据库
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        dataSource.setUsername("root");
        dataSource.setPassword("root123");
        return dataSource;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }
}
