package com.xhgj.redismq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 周宇
 * @create 2022-02-27 9:02
 */
@SpringBootTest
public class TestZSetVer {

    @Autowired
    private ZSetVer zSetVer;

    @Test
    public void testConsumerDelayMessage() {
        zSetVer.consumerDelayMessage();
    }

    @Test
    public void testProducer() {
        zSetVer.producer();
    }
}
