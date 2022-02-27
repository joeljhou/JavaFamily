package com.xhgj.redismq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 周宇
 * @create 2022-02-27 2:00
 */
@SpringBootTest
public class TestListVer {

    @Autowired
    private ListVer listVer;

    @Test
    public void testGet() {
        List<String> listmq = listVer.get("listmq");
        for (int i = 0; i < listmq.size(); i++) {
            String message = listmq.get(i);
            if (i % 2 == 0) {
                message = "key=" + message;
            } else {
                message = "value=" + message;
            }
            System.out.println(message);
        }
    }

    @Test
    public void testPut() {
        listVer.put("listmq", "msgtest");
    }
}
