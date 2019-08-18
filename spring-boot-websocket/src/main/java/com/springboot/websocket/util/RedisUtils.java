package com.springboot.websocket.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: Arthas
 * @date: 2019-08-18 12:18
 * @description:
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 插入Map
     *
     * @param key
     * @param hashKey
     * @param hashValue
     */
    public void setMap(String key, String hashKey, Object hashValue) {
        HashOperations operations = redisTemplate.opsForHash();
        operations.put(key, hashKey, hashValue);
    }

    /**
     * 获取相应的HashMap
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object getMapValue(String key, String hashKey) {
        HashOperations operations = redisTemplate.opsForHash();
        return operations.get(key, hashKey);
    }

    /**
     * 判断是否存在对应的HashMap
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Boolean hashMapKey(String key, String hashKey) {
        HashOperations operations = redisTemplate.opsForHash();
        return operations.hasKey(key, hashKey);
    }

    /**
     * 删除相应的Map
     *
     * @param key
     * @param hashKey
     * @return
     */
    public void deleteMapKey(String key, String hashKey) {
        HashOperations operations = redisTemplate.opsForHash();
        operations.delete(key, hashKey);
    }
}
