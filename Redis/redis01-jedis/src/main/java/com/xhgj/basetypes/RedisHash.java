package com.xhgj.basetypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 周宇
 * @create 2022-02-27 1:22
 * 操作Hash类型
 */
@Component
public class RedisHash {

    public final static String RS_HASH_NS = "rh:";

    @Autowired
    private JedisPool jedisPool;

    /**
     * 向Redis中存储，永久有效
     */
    public Long set(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hset(RS_HASH_NS + key, field, value);
        } catch (Exception e) {
            throw new RuntimeException("向Redis存储值失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String get(String key, String field) {
        return getRaw(RS_HASH_NS + key, field);
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String getRaw(String key, String field) {
        Jedis jedis = null;
        String values;
        try {
            jedis = jedisPool.getResource();
            values = jedis.hget(key, field);
            return values;
        } catch (Exception e) {
            throw new RuntimeException("获取Redis值失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
