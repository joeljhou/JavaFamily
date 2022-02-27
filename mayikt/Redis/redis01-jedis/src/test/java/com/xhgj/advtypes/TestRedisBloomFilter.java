package com.xhgj.advtypes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 周宇
 * @create 2022-02-27 21:07
 */
@SpringBootTest
public class TestRedisBloomFilter {

    //一天的秒数
    private static final int DAY_SEC = 60 * 60 * 24;

    @Autowired
    private RedisBloomFilter redisBloomFilter;

    @Test
    public void testInsert() {
        //帖子8839540在日期2022年02月27日被5个用户访问过
        System.out.println("redisBloomFilter");
        redisBloomFilter.insert("topic_read:8839540:20220227", "76930242", DAY_SEC);
        redisBloomFilter.insert("topic_read:8839540:20220227", "76930243", DAY_SEC);
        redisBloomFilter.insert("topic_read:8839540:20220227", "76930244", DAY_SEC);
        redisBloomFilter.insert("topic_read:8839540:20220227", "76930245", DAY_SEC);
        redisBloomFilter.insert("topic_read:8839540:20220227", "76930246", DAY_SEC);
    }

    @Test
    public void testMayExist() {
        //检查一下4个用户在日期2022年02月27日是否访问过帖子8839540
        System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20220227", "76930242"));
        System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20220227", "76930244"));
        System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20220227", "76930246"));
        System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20220227", "76930250"));
    }

}
