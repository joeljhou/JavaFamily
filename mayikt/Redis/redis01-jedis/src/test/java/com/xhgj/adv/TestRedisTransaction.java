package com.xhgj.adv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 周宇
 * @create 2022-02-28 2:39
 */
@SpringBootTest
public class TestRedisTransaction {

    @Autowired
    private RedisTransaction redisTransaction;

    @Test
    public void testTransaction(){
        redisTransaction.transaction();
    }

}
