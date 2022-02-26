package com.xhgj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 周宇
 * @create 2022-02-26 13:23
 */
@RestController("/jedisPool")
public class RedisTestController {

    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/test")
    public String test() {
        //从连接池中获取连接资源
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth("123456");
            jedis.set("a", "a");
            String a = jedis.get("a");
            return a;
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @GetMapping("test2")
    public void test2(){

    }

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        System.out.println(config.getTestOnBorrow());
        System.out.println(config.getTestOnReturn());
        System.out.println(config.getTestOnCreate());
        System.out.println(config.getBlockWhenExhausted());
        System.out.println(config.getJmxEnabled());
    }

}
