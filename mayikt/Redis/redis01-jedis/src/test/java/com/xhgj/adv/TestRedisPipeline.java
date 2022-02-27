package com.xhgj.adv;

import com.xhgj.basetypes.RedisString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2022-02-28 1:57
 */
@SpringBootTest
public class TestRedisPipeline {

    @Autowired
    private RedisPipeline redisPipeline;

    @Autowired
    private RedisString redisString;

    private static final int TEST_COUNT = 1000;

    @Test
    public void testPipline() {
        long setStart = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            redisString.set("testStringM:key_" + i, String.valueOf(i));
        }
        long setEnd = System.currentTimeMillis();
        System.out.println("非pipline操作" + TEST_COUNT + "次字符串数据类型set写入，耗时:" + (setEnd - setStart) + "毫秒");

        List<String> keys = new ArrayList<>(TEST_COUNT);
        List<String> values = new ArrayList<>(TEST_COUNT);
        for (int i = 0; i < keys.size(); i++) {
            keys.add("testpiplineM:key_" + i);
            values.add(String.valueOf(i));
        }
        long piplineStart = System.currentTimeMillis();
        redisPipeline.plSet(keys, values);
        long piplineEnd = System.currentTimeMillis();
        System.out.println("pipline操作" + TEST_COUNT + "次字符串数据类型set写入，耗时:" + (piplineEnd - piplineStart) + "毫秒");
    }

}
