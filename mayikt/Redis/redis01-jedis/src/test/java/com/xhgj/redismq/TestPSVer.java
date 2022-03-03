package com.xhgj.redismq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 周宇
 * @create 2022-03-04 1:15
 */
@SpringBootTest
public class TestPSVer {

    @Autowired
    public PSVer psVer;

    @Test
    public void TestSub() {
        psVer.sub("channel1", "channel2");
    }

    @Test
    public void TestPub() {
        psVer.pub("channel1","msg1");
        psVer.pub("channel2","msg2");
    }

}
