package com.xhgj.advtypes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 周宇
 * @create 2022-02-27 23:16
 */
@SpringBootTest
public class TestRedisHLL {

    @Autowired
    private RedisHLL redisHLL;

    @Test
    public void testCount(){
        redisHLL.count();
    }

}
