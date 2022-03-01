package com.xhgj.adv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 周宇
 * @create 2022-03-02 2:30
 */
@SpringBootTest
public class TestRedisLua {

    @Autowired
    private RedisLua redisLua;

    @Test
    public void TestLoadScripts(){
        System.out.println(redisLua.loadScripts());
    }

    @Test
    public void TestIpLimitFlow(){
        System.out.println(redisLua.ipLimitFlow("lua"));
    }

}
