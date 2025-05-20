package com.kit.common.cache;

import com.kit.common.serializer.HessianSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: kiturone
 * @date: 2018-11-30 14:08
 * @description:
 */
public class RedisCacheClient implements CacheClient {

    private static Log log = LogFactory.getLog(RedisCacheClient.class);

    private ShardedJedisPool shardedJedisPool;

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    @Autowired
    private HessianSerializer serializer;

    @Override
    public <T> String set(String field, String key, T value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.set(rawKey(field, key), rawValue(value));
        } catch (Exception e) {
            log.error("redis set error key: " + key(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <T> String setex(String field, String key, T value, int expireTime) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.setex(rawKey(field, key), expireTime, rawValue(value));
        } catch (Exception ex) {
            log.error("redis set error key:" + key(field, key), ex);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <T> String setnx(String key, T value, int expireTime) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            SetParams params = new SetParams()
                    .nx()
                    .px(expireTime);
            return shardedJedis.set(key, String.valueOf(value), params);
        } catch (Exception ex) {
            log.error("redis set error key:" + key, ex);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <T> T get(String field, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return (T) serializer.deserialize(shardedJedis.get(rawKey(field, key)));
        } catch (Exception e) {
            log.error("redis get error key: " + key(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public long del(String field, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.del(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis del error key: " + key(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return 0;
    }

    @Override
    public boolean exists(String field, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.exists(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis exists error key: " + key(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return false;
    }

    @Override
    public long ttl(String field, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.ttl(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis ttl error key: " + key(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return 0;
    }

    @Override
    public <M, N> Long hset(String namespace, String key, M field, N value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.hset(rawKey(namespace, key), rawValue(field), rawValue(value));
        } catch (Exception e) {
            log.error("redis hset error key: " + key(namespace, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <M, N> M hget(String namespace, String key, N field) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return (M) serializer.deserialize(shardedJedis.hget(rawKey(namespace, key), rawValue(field)));
        } catch (Exception e) {
            log.error("redis hget error key: " + key(namespace, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long hlen(String namespace, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.hlen(rawKey(namespace, key));
        } catch (Exception e) {
            log.error("redis hlen error key: " + key(namespace, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <M, N> List<M> hmget(String namespace, String key, N... fields) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            byte[][] fieldBytes = new byte[fields.length][];
            for (int i = 0, len = fields.length; i < len; i++) {
                fieldBytes[i] = rawValue(fields[i]);
            }

            List<M> valueList = new ArrayList<>();
            List<byte[]> result = shardedJedis.hmget(rawKey(namespace, key), fieldBytes);
            for (byte[] t : result) {
                valueList.add((M) serializer.deserialize(t));
            }
            return valueList;
        } catch (Exception ex) {
            log.error("redis hmget error.key:" + key(namespace, key), ex);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <M, N> String hmset(String namespace, String key, Map<M, N> hash) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            if (log.isDebugEnabled()) {
                log.debug("hmset key: " + key(namespace, key));
            }

            Map<byte[], byte[]> newHash = new HashMap<>();
            for (Map.Entry m : hash.entrySet()) {
                newHash.put(rawValue((M) (m.getKey())), rawValue((N) (m.getValue())));
            }
            return shardedJedis.hmset(rawKey(namespace, key), newHash);
        } catch (Exception ex) {
            log.error("redis hmset error.key:" + key(namespace, key), ex);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public <T> Long expire(String field, String key, int expireTime) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.expire(rawKey(field, key), expireTime);
        } catch (Exception e) {
            log.error("redis expire errot. key : " + rawKey(field, key), e);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long incr(String field, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            if (log.isDebugEnabled()) {
                log.debug("incr key: " + key(field, key));
            }
            return shardedJedis.incr(rawKey(field, key));
        } catch (Exception ex) {
            log.error("redis incr error.key:" + key(field, key), ex);
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public Long incrby(String field, String key, long increment) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.incrBy(rawKey(field, key), increment);
        } catch (Exception e) {
            log.error("redis incrby error. key : " + key(field, key));
        } finally {
            shardedJedis.close();
        }
        return null;
    }

    @Override
    public byte[] getRaw(String field, String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.get(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis getRaw error. key : " + key(field, key));
        } finally {
            shardedJedis.close();
        }
        return new byte[0];
    }


    /**
     * 拼接key
     *
     * @param field namespace
     * @param key   redis key
     */
    protected String key(String field, String key) {
        if (key == null || "".equals(key)) {
            return null;
        }
        return (field + "_" + key);
    }

    /**
     * 生成redis中存储的实际key
     *
     * @param namespace redis namespace
     * @param key       redis  key
     * @return
     */
    protected byte[] rawKey(String namespace, String key) {
        if (key == null || "".equals(key)) {
            return null;
        }
        return serializer.serialize((namespace + "_" + key));
    }

    /**
     * 序列化 value值
     *
     * @param value redis value
     */
    protected byte[] rawValue(Object value) {
        if (null == value) {
            throw new JedisException("value can not be null.");
        }
        return serializer.serialize(value);
    }
}
