package com.xhgj.redismq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author 周宇
 * @create 2022-02-27 1:46
 * 基于List实现的简单消息中间件
 */
@Component
public class ListVer {
    public final static String RS_LIST_MQ_NS = "rlm:";

    @Autowired
    private JedisPool jedisPool;

    /**
     * 消费者接收消息
     */
    public List<String> get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //阻塞式获取
            return jedis.brpop(0, RS_LIST_MQ_NS + key);
        } catch (Exception e) {
            throw new RuntimeException("接收消息失败" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 生产者发送消息
     */
    public void put(String key, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(RS_LIST_MQ_NS + key, message);
        } catch (Exception e) {
            throw new RuntimeException("发送消息失败" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
