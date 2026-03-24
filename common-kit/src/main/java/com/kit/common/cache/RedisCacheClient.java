package com.kit.common.cache;

import com.kit.common.serializer.HessianSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: kiturone
 * @date: 2018-11-30 14:08
 * @description: Redis cache client using JedisPool
 */
public class RedisCacheClient implements CacheClient {

    private static Log log = LogFactory.getLog(RedisCacheClient.class);

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * @deprecated Use {@link #getJedisPool()} instead
     */
    @Deprecated
    public JedisPool getShardedJedisPool() {
        return jedisPool;
    }

    /**
     * @deprecated Use {@link #setJedisPool(JedisPool)} instead
     */
    @Deprecated
    public void setShardedJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Autowired
    private HessianSerializer serializer;

    @Override
    public <T> String set(String field, String key, T value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(rawKey(field, key), rawValue(value));
        } catch (Exception e) {
            log.error("redis set error key: " + key(field, key), e);
        }
        return null;
    }

    @Override
    public <T> String setex(String field, String key, T value, int expireTime) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setex(rawKey(field, key), expireTime, rawValue(value));
        } catch (Exception ex) {
            log.error("redis set error key:" + key(field, key), ex);
        }
        return null;
    }

    @Override
    public <T> String setnx(String key, T value, int expireTime) {
        try (Jedis jedis = jedisPool.getResource()) {
            SetParams params = new SetParams()
                    .nx()
                    .px(expireTime);
            return jedis.set(key, String.valueOf(value), params);
        } catch (Exception ex) {
            log.error("redis set error key:" + key, ex);
        }
        return null;
    }

    @Override
    public <T> T get(String field, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return (T) serializer.deserialize(jedis.get(rawKey(field, key)));
        } catch (Exception e) {
            log.error("redis get error key: " + key(field, key), e);
        }
        return null;
    }

    @Override
    public long del(String field, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis del error key: " + key(field, key), e);
        }
        return 0;
    }

    @Override
    public boolean exists(String field, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis exists error key: " + key(field, key), e);
        }
        return false;
    }

    @Override
    public long ttl(String field, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.ttl(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis ttl error key: " + key(field, key), e);
        }
        return 0;
    }

    @Override
    public <M, N> Long hset(String namespace, String key, M field, N value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(rawKey(namespace, key), rawValue(field), rawValue(value));
        } catch (Exception e) {
            log.error("redis hset error key: " + key(namespace, key), e);
        }
        return null;
    }

    @Override
    public <M, N> M hget(String namespace, String key, N field) {
        try (Jedis jedis = jedisPool.getResource()) {
            return (M) serializer.deserialize(jedis.hget(rawKey(namespace, key), rawValue(field)));
        } catch (Exception e) {
            log.error("redis hget error key: " + key(namespace, key), e);
        }
        return null;
    }

    @Override
    public Long hlen(String namespace, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hlen(rawKey(namespace, key));
        } catch (Exception e) {
            log.error("redis hlen error key: " + key(namespace, key), e);
        }
        return null;
    }

    @Override
    public <M, N> List<M> hmget(String namespace, String key, N... fields) {
        try (Jedis jedis = jedisPool.getResource()) {
            byte[][] fieldBytes = new byte[fields.length][];
            for (int i = 0, len = fields.length; i < len; i++) {
                fieldBytes[i] = rawValue(fields[i]);
            }

            List<M> valueList = new ArrayList<>();
            List<byte[]> result = jedis.hmget(rawKey(namespace, key), fieldBytes);
            for (byte[] t : result) {
                valueList.add((M) serializer.deserialize(t));
            }
            return valueList;
        } catch (Exception ex) {
            log.error("redis hmget error.key:" + key(namespace, key), ex);
        }
        return null;
    }

    @Override
    public <M, N> String hmset(String namespace, String key, Map<M, N> hash) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (log.isDebugEnabled()) {
                log.debug("hmset key: " + key(namespace, key));
            }

            Map<byte[], byte[]> newHash = new HashMap<>();
            for (Map.Entry m : hash.entrySet()) {
                newHash.put(rawValue((M) (m.getKey())), rawValue((N) (m.getValue())));
            }
            return jedis.hmset(rawKey(namespace, key), newHash);
        } catch (Exception ex) {
            log.error("redis hmset error.key:" + key(namespace, key), ex);
        }
        return null;
    }

    @Override
    public <T> Long expire(String field, String key, int expireTime) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(rawKey(field, key), expireTime);
        } catch (Exception e) {
            log.error("redis expire errot. key : " + rawKey(field, key), e);
        }
        return null;
    }

    @Override
    public Long incr(String field, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (log.isDebugEnabled()) {
                log.debug("incr key: " + key(field, key));
            }
            return jedis.incr(rawKey(field, key));
        } catch (Exception ex) {
            log.error("redis incr error.key:" + key(field, key), ex);
        }
        return null;
    }

    @Override
    public Long incrby(String field, String key, long increment) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incrBy(rawKey(field, key), increment);
        } catch (Exception e) {
            log.error("redis incrby error. key : " + key(field, key));
        }
        return null;
    }

    @Override
    public byte[] getRaw(String field, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(rawKey(field, key));
        } catch (Exception e) {
            log.error("redis getRaw error. key : " + key(field, key));
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