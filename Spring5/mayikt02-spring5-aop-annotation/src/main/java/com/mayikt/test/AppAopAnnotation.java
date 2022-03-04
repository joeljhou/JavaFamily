package com.mayikt.test;

import com.mayikt.config.MyConfig;
import com.mayikt.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-24 11:29
 */
public class AppAopAnnotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.addOrder();
    }
}
