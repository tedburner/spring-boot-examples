package com.springboot.shardingsphere.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author: Arthas
 * @date: 2018-12-28 11:50
 * @description:
 */
public class ShardingConfig implements PreciseShardingAlgorithm, RangeShardingAlgorithm {

    /**
     * 精确分片算法
     * 将user_id的值和5比较，如果小于等于5，数据对应的表为：USER_AUTH_1
     * 将user_id的值和5比较，如果大于5，数据对应的表为：USER_AUTH_2
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        if (availableTargetNames.contains("user_auth")) {
            if (shardingValue.getColumnName().equalsIgnoreCase("USER_ID")) {
                Comparable value = shardingValue.getValue();
                int i = value.compareTo(5);
                int suffix = -1;
                if (i <= 0) {
                    suffix = 1;
                } else {
                    suffix = 2;
                }
                String s = ("user_auth_" + suffix).toUpperCase();
                return s;
            }
        }
        return null;
    }

    /**
     * lower 为 between and 中的较小值
     * upper 为 between and 中的较大值
     * 如果lower小于等于5，tables集合中就要加入USER_AUTH_1表
     * 如果lower大于5，tables集合中就要加入USER_AUTH_2表
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {

        Collection<String> tables = new HashSet<>();
        if (availableTargetNames.contains("user_auth")) {
            if (shardingValue.getColumnName().equalsIgnoreCase("USER_ID")) {
                Comparable lower = shardingValue.getValueRange().lowerEndpoint();
                Comparable upper = shardingValue.getValueRange().upperEndpoint();

                int lowerResult = lower.compareTo(5);
                int upperResult = upper.compareTo(5);

                if (lowerResult <= 0) {
                    tables.add("user_auth_1".toUpperCase());
                    if (upperResult > 0) {
                        tables.add("user_auth_2".toUpperCase());
                    }
                } else {
                    tables.add("user_auth_2".toUpperCase());
                }
            }
        }
        return tables;
    }
}
