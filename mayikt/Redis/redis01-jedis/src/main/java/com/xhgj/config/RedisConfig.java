package com.xhgj.config;

import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * @author 周宇
 * @create 2022-02-26 13:02
 */
@Configuration
public class RedisConfig {

    /**
     * Redis服务器连接主机
     */
    @Value("${spring.redis.host}")
    private String host;

    /**
     * Redis服务器连接端口
     */
    @Value("${spring.redis.port}")
    private int port;

    /**
     * Redis服务器连接密码
     */
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 连接池最大连接数（使用负值表示没有限制）
     */
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    /**
     * 连接池中的最大空闲连接
     */
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    /**
     * 连接池中的最小空闲连接
     */
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    /**
     * 连接池最大阻塞等待时间（使用负值表示没有限制）
     */
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    /**
     * 连接超时时间（毫秒）
     */
    @Value("${spring.redis.timeout}")
    private int timeout;

    private JedisPool jedisPool = null;

    //初始化redisWrapper (PostConstruct注解相当于静态代码库,方法会在类初始化的时候 进行执行)
    @PostConstruct
    public void init() throws RedisException {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxActive);//设置连接池中最多允放置100个Jedis对象
            config.setMaxIdle(maxIdle);//设置连接池中最大允许的50个空闲连接
            config.setMinIdle(minIdle);//设置连接池中最小允许的10个空闲连接
            config.setMaxWaitMillis(maxWaitMillis);//没有资源时最长等待1秒,1秒后还没有的话就报错
            config.setTestOnBorrow(false);//借出连接的时候是否测试有效性，默认false
            config.setTestOnReturn(false);//归还时是否测试，默认false
            config.setTestOnCreate(false);//创建时是否测试有效，默认false 生产环境设建议设置成true
            config.setBlockWhenExhausted(true);//当连接池jedis无可用资源时是否等待资源，默认true
            config.setJmxEnabled(true);//启用pool的jmx管理功能，默认true
            jedisPool = new JedisPool(config, host, port, timeout, password);
        } catch (Exception e) {
            throw new RedisException("初始化redisPool失败");   //抛出异常
        }
    }

    @Bean
    public JedisPool getJedisPool() {
        return jedisPool;
    }

}
