package com.xhgj.advtypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 周宇
 * @create 2022-02-27 23:12
 * HyperLogLog使用 统计基数，误差0.81%
 */
@Component
public class RedisHLL {

    public final static String RS_HLL_NS = "rhll:";

    @Autowired
    private JedisPool jedisPool;

    public void count() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //1000个用户访问
            for (int i = 0; i < 1000; i++) {
                jedis.pfadd(RS_HLL_NS + "countest", "user" + i);
            }
            long total = jedis.pfcount(RS_HLL_NS + "countest");
            System.out.println("实际次数：" + 1000 + ",统计次数：" + total);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
