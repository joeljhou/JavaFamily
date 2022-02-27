package com.xhgj.config;

import com.xhgj.advtypes.RedisBloomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 周宇
 * @create 2022-02-27 21:16
 */
@Configuration
public class AppBloomFilterConfig {

    /*可以放入配置文件或者应用自行设置*/
    private static final int NUM_APPOX_ELEMENTS = 3000;
    private static final double FPP = 0.03;

    @Bean
    public RedisBloomFilter getRedisBloomFilter() {
        RedisBloomFilter redisBloomFilter = new RedisBloomFilter();
        redisBloomFilter.init(NUM_APPOX_ELEMENTS, FPP);
        return redisBloomFilter;
    }

}
