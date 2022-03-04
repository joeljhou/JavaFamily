package com.mayikt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.mayikt.mapper")
@SpringBootApplication
public class AppOrder {

    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class, args);
    }

}
