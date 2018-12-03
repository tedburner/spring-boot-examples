package com.kit.common.redis;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.BinaryShardedJedis;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author: Lucifer
 * @date: 2018-11-30 14:33
 * @description:
 */
public class ShardedJedis extends BinaryShardedJedis implements JedisCommands, Closeable {


    public void resetState() {
        for (Jedis jedis : getAllShards()) {
            jedis.resetState();
        }
    }
    protected Pool<ShardedJedis> dataSource = null;

    public Pool<ShardedJedis> getDataSource() {
        return dataSource;
    }

    public void setDataSource(Pool<ShardedJedis> dataSource) {
        this.dataSource = dataSource;
    }

    public ShardedJedis(List<JedisShardInfo> shards) {
        super(shards);
    }

    public ShardedJedis(List<JedisShardInfo> shards, Hashing algo) {
        super(shards, algo);
    }

    public ShardedJedis(List<JedisShardInfo> shards, Pattern keyTagPattern) {
        super(shards, keyTagPattern);
    }

    public ShardedJedis(List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
        super(shards, algo, keyTagPattern);
    }

    @Override
    public void close() {
        if (dataSource != null) {
            boolean broken = false;

            for (Jedis jedis : getAllShards()) {
                if (jedis.getClient().isBroken()) {
                    broken = true;
                    break;
                }
            }

            if (broken) {
                dataSource.returnBrokenResource(this);
            } else {
                dataSource.returnResource(this);
            }

        } else {
            disconnect();
        }
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = getShard(key);
        return jedis.set(key, value);
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = getShard(key);
        return jedis.set(key, value, nxxx, expx, time);
    }

    @Override
    public String set(String key, String value, String nxxx) {
        Jedis jedis = getShard(key);
        return jedis.set(key, value, nxxx);
    }

    @Override
    public String get(String key) {
        Jedis jedis = getShard(key);
        return jedis.get(key);
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = getShard(key);
        return jedis.exists(key);
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = getShard(key);
        return jedis.persist(key);
    }

    @Override
    public String type(String key) {
        Jedis jedis = getShard(key);
        return jedis.type(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return null;
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return null;
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Long pttl(String key) {
        return null;
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return null;
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return null;
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return null;
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return null;
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return null;
    }

    @Override
    public String getSet(String key, String value) {
        return null;
    }

    @Override
    public Long setnx(String key, String value) {
        return null;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return null;
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        return null;
    }

    @Override
    public Long decrBy(String key, long integer) {
        return null;
    }

    @Override
    public Long decr(String key) {
        return null;
    }

    @Override
    public Long incrBy(String key, long integer) {
        return null;
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long append(String key, String value) {
        return null;
    }

    @Override
    public String substr(String key, int start, int end) {
        return null;
    }

    @Override
    public Long hset(String key, String field, String value) {
        return null;
    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return null;
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return null;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return null;
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return null;
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return null;
    }

    @Override
    public Boolean hexists(String key, String field) {
        return null;
    }

    @Override
    public Long hdel(String key, String... field) {
        return null;
    }

    @Override
    public Long hlen(String key) {
        return null;
    }

    @Override
    public Set<String> hkeys(String key) {
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return null;
    }

    @Override
    public Long rpush(String key, String... string) {
        return null;
    }

    @Override
    public Long lpush(String key, String... string) {
        return null;
    }

    @Override
    public Long llen(String key) {
        return null;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return null;
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return null;
    }

    @Override
    public String lindex(String key, long index) {
        return null;
    }

    @Override
    public String lset(String key, long index, String value) {
        return null;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return null;
    }

    @Override
    public String lpop(String key) {
        return null;
    }

    @Override
    public String rpop(String key) {
        return null;
    }

    @Override
    public Long sadd(String key, String... member) {
        return null;
    }

    @Override
    public Set<String> smembers(String key) {
        return null;
    }

    @Override
    public Long srem(String key, String... member) {
        return null;
    }

    @Override
    public String spop(String key) {
        return null;
    }

    @Override
    public Set<String> spop(String key, long count) {
        return null;
    }

    @Override
    public Long scard(String key) {
        return null;
    }

    @Override
    public Boolean sismember(String key, String member) {
        return null;
    }

    @Override
    public String srandmember(String key) {
        return null;
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return null;
    }

    @Override
    public Long strlen(String key) {
        return null;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return null;
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        return null;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return null;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return null;
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return null;
    }

    @Override
    public Long zrem(String key, String... member) {
        return null;
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return null;
    }

    @Override
    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        return null;
    }

    @Override
    public Long zrank(String key, String member) {
        return null;
    }

    @Override
    public Long zrevrank(String key, String member) {
        return null;
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return null;
    }

    @Override
    public Long zcard(String key) {
        return null;
    }

    @Override
    public Double zscore(String key, String member) {
        return null;
    }

    @Override
    public List<String> sort(String key) {
        return null;
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return null;
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return null;
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return null;
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return null;
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        return null;
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        return null;
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return null;
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return null;
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return null;
    }

    @Override
    public Long lpushx(String key, String... string) {
        return null;
    }

    @Override
    public Long rpushx(String key, String... string) {
        return null;
    }

    @Override
    public List<String> blpop(String arg) {
        return null;
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return null;
    }

    @Override
    public List<String> brpop(String arg) {
        return null;
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return null;
    }

    @Override
    public Long del(String key) {
        return null;
    }

    @Override
    public String echo(String string) {
        return null;
    }

    @Override
    public Long move(String key, int dbIndex) {
        return null;
    }

    @Override
    public Long bitcount(String key) {
        return null;
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return null;
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return null;
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String key, int cursor) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, int cursor) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return null;
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return null;
    }

    @Override
    public long pfcount(String key) {
        return 0;
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return null;
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return null;
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return null;
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return null;
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return null;
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return null;
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        return null;
    }


}
