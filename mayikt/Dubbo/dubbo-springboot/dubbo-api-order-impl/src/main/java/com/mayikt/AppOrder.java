package com.mayikt;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 周宇
 * @create 2021-09-15 12:27
 */
@EnableDubbo
@SpringBootApplication
public class AppOrder {
    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class, args);
    }
}
