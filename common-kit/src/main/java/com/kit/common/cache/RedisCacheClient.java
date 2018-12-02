package com.kit.common.cache;

import com.kit.common.redis.ShardedJedis;
import com.kit.common.redis.KitShardedJedisPool;
import com.kit.common.serialize.HessianSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Map;

/**
 * @author: Lucifer
 * @date: 2018-11-30 14:08
 * @description:
 */
@Component
public class RedisCacheClient implements CacheClient {

    private static Log log = LogFactory.getLog(RedisCacheClient.class);

    @Autowired
    private KitShardedJedisPool shardedJedisPool;

    private HessianSerializer serialize;

    public HessianSerializer getSerialize() {
        return serialize;
    }

    public void setSerialize(HessianSerializer serialize) {
        this.serialize = serialize;
    }

    @Override
    public <T> String set(String field, String key, T value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            if (log.isDebugEnabled()) {
                log.debug("set key: " + key(field, key));
            }
            return shardedJedis.set(rawKey(field, key), rawValue(value));
        } catch (Exception e) {
            log.error("redis set error key: " + key(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <T> String set(String field, String key, T value, int expireTime) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            if (log.isDebugEnabled()) {
                log.debug("set key: " + key(field, key));
            }
            return shardedJedis.setex(rawKey(field, key), expireTime, rawValue(value));
        } catch (Exception ex) {
            log.error("edis set error key:" + key(field, key), ex);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <T> T get(String field, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            if (log.isDebugEnabled()) {
                log.debug("get key: " + key(field, key));
            }
            return (T) serialize.deserialize(shardedJedis.get(rawKey(field, key)));
        } catch (Exception e) {
            log.error("redis get error key: " + key(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public long del(String field, String key) {
        return 0;
    }

    @Override
    public boolean exists(String field, String key) {
        return false;
    }

    @Override
    public long ttl(String field, String key) {
        return 0;
    }

    @Override
    public <M, N> Long hset(String namespace, String key, M field, N value) {
        return null;
    }

    @Override
    public <M, N> M hget(String namespace, String key, N field) {
        return null;
    }

    @Override
    public Long hlen(String namespace, String key) {
        return null;
    }

    @Override
    public <M, N> List<M> hmget(String namespace, String key, N... fields) {
        return null;
    }

    @Override
    public <M, N> String hmset(String namespace, String key, Map<M, N> hash) {
        return null;
    }

    @Override
    public <T> Long expire(String field, String key, int expireTime) {
        return null;
    }

    @Override
    public Long incr(String field, String key) {
        return null;
    }

    @Override
    public Long incrby(String field, String key, long increment) {
        return null;
    }

    @Override
    public byte[] getRaw(String field, String key) {
        return new byte[0];
    }

    @Override
    public String getLiteralVal(String field, String key) {
        return null;
    }

    @Override
    public boolean existsKey(String field, String key) {
        return false;
    }

    @Override
    public Long expireKey(String field, String key, int expireTime) {
        return null;
    }

    @Override
    public <T> List<T> mget(String field, List<String> keys) {
        return null;
    }

    protected String key(String field, String key) {
        if (key == null || "".equals(key)) {
            return null;
        }
        return (field + "_" + key);
    }

    /**
     * 生成redis中存储的实际key
     *
     * @param namespace
     * @param key
     * @return
     */
    protected byte[] rawKey(String namespace, String key) {
        if (key == null || "".equals(key)) {
            return null;
        }
        return (namespace + "_" + key).getBytes();
    }

    protected byte[] rawValue(Object value) {
        if (null == value) {
            throw new JedisException("value can not be null.");
        }
        return serialize.serialize(value);
    }
}
