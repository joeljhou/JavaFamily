package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

//启动异步注解 @Async实际就是多线程封装的
//异步线程执行方法有可能会非常消耗cpu的资源，所以大的项目建议使用 Mq异步实现。
@EnableAsync
@SpringBootApplication
public class Springboot09AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot09AsyncApplication.class, args);
    }

}
