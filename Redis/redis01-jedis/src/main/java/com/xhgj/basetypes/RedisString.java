package com.xhgj.basetypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 周宇
 * @create 2022-02-26 13:43
 * 操作String类型
 */
@Component
public class RedisString {

    public final static String RS_STR_NS = "rs:";

    @Autowired
    private JedisPool jedisPool;

    /**
     * 向Redis中存值
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(RS_STR_NS + key, value);
        } catch (Exception e) {
            throw new RuntimeException("向Redis中存值失败！\n" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 批量向Redis中存值，永久有效
     */
    public String msetRaw(String... keysvalue) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /*拆解加前缀*/
            for (int i = 0; i < keysvalue.length; i++) {
                if (i % 2 == 0) {
                    keysvalue[i] = RS_STR_NS + keysvalue;
                }
            }
            return jedis.mset(keysvalue);
        } catch (Exception e) {
            throw new RuntimeException("批量向Redis中存值失败！\n" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据传入key获取指定value
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(RS_STR_NS + key);
        } catch (Exception e) {
            throw new RuntimeException("获取Redis值失败！\n" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
