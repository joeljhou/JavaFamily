package com.xhgj.adv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @author 周宇
 * @create 2022-02-28 1:47
 */
@Component
public class RedisPipeline {

    @Autowired
    private JedisPool jedisPool;

    public List<Object> plGet(List<String> keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipelined = jedis.pipelined();
            for (String key : keys) {
                pipelined.get(key);
            }
            return pipelined.syncAndReturnAll();
        } catch (Exception e) {
            throw new RuntimeException("执行Pipline获取失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void plSet(List<String> keys, List<String> values) {
        if (keys.size() != values.size()) {
            throw new RuntimeException("key和value个数不匹配");
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipelined = jedis.pipelined();
            for (int i = 0; i < keys.size(); i++) {
                pipelined.set(keys.get(i), values.get(i));
            }
            pipelined.sync();
        } catch (Exception e) {
            throw new RuntimeException("执行Pipline设值失败！" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
