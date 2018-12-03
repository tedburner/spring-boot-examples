package com.kit.common.cache;

import java.util.List;
import java.util.Map;

/**
 * @author: Lucifer
 * @date: 2018-11-30 15:06
 * @description:
 */
public interface CacheClient {

    /**
     * 设置指定key的value
     *
     * @param field Redis namespace
     * @param key   redis key
     * @param value redis 值
     * @return
     */
    <T> String set(String field, String key, T value);

    /**
     * 设置指定key的value
     *
     * @param field
     * @param key
     * @param value
     * @return
     */
    <T> String set(String field, String key, T value, int expireTime);

    /**
     * 获取指定key的value
     *
     * @param field
     * @param key
     * @return
     */
    <T> T get(String field, String key);

    /**
     * 删除指定key的value
     *
     * @param field
     * @param key
     * @return
     */
    long del(String field, String key);

    /**
     * 删除指定key的value
     *
     * @param field
     * @param key
     * @return
     */
    boolean exists(String field, String key);

    /**
     * 返回指定key存活的时间
     *
     * @param field
     * @param key
     * @return
     */
    long ttl(String field, String key);

    /**
     * hash set
     *
     * @param namespace
     * @param key
     * @param field
     * @param value
     * @param <M>
     * @param <N>
     * @return
     */
    <M, N> Long hset(String namespace, String key, M field, N value);

    /**
     * hash get
     *
     * @param namespace
     * @param key
     * @param field
     * @param <M>
     * @return
     */
    <M, N> M hget(String namespace, String key, N field);

    /**
     * hash, Returns the number of fields contained in the hash stored at key
     *
     * @param namespace
     * @param key
     * @return 0 when key does not exist
     */
    Long hlen(String namespace, String key);

    /**
     * hash mget
     *
     * @param namespace
     * @param key
     * @param fields
     * @return
     */
    <M, N> List<M> hmget(String namespace, String key, N... fields);

    /**
     * has mset
     *
     * @param namespace
     * @param key
     * @param hash
     * @param <M>
     * @param <N>
     * @return
     */
    <M, N> String hmset(String namespace, String key, Map<M, N> hash);

    /**
     * 设置过期时间
     *
     * @param field
     * @param key
     * @param expireTime
     * @return
     */
    <T> Long expire(String field, String key, int expireTime);

    /**
     * 自增1
     *
     * @param field
     * @param key
     * @return
     */
    Long incr(String field, String key);

    /**
     * 自增n
     *
     * @param field
     * @param key
     * @param increment
     * @return
     */
    Long incrby(String field, String key, long increment);

    /**
     * 获取原始值
     *
     * @param field
     * @param key
     * @return
     */
    byte[] getRaw(String field, String key);

    /**
     * 批量获取多个key ShardedJedis 原生的不支持mget 需要重写 mget
     * https://github.com/xetorthio/jedis/issues/149
     *
     * @param keys
     * @return
     */
    //<T> List<T> mget(String field, List<String> keys);

}
