package com.mayikt.consumer;

import com.mayikt.demo.api.service.DemoApiService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-09-10 23:49
 * 消费者调用生产者服务
 */
public class ConsumerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        DemoApiService demoApiService = context.getBean(DemoApiService.class);
        String result = demoApiService.getUser(1L);
        System.out.println("result:" + result);
    }
}
