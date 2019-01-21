package com.springboot.shardingsphere.config;

import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author: Arthas
 * @date: 2018-12-28 11:50
 * @description:
 */
@Configuration
public class ShardingConfig implements PreciseShardingAlgorithm<Integer>, RangeShardingAlgorithm<Long> {

    /**
     * 精确分片算法
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        int size = availableTargetNames.size();
        for (String each : availableTargetNames) {
            if (each.startsWith(shardingValue.getValue() % size + "")) {
                return each;
            }
        }
        return null;
    }

    /**
     * 分表算法
     *
     * @param availableTargetNames
     * @param rangeShardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> rangeShardingValue) {
        int size = availableTargetNames.size();
        Collection<String> tables = new HashSet<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % size + "")) {
                    tables.add(each);
                }
            }
        }
        return tables;
    }
}
