package com.xhgj;

import com.xhgj.basetypes.RedisString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestRedisString {

    @Autowired
    private RedisString redisString;

    @Test
    public void testSet() {
        System.out.println(redisString.set("test", "Hello Java"));
    }

    @Test
    void testGet() {
        System.out.println(redisString.get("test"));
    }

    @Test
    void testMset() {
        System.out.println(redisString.msetRaw("test1", "Hello a",
                "test2", "Hello b", "test3", "Hello c", "test4", "Hello d"));
    }

}
