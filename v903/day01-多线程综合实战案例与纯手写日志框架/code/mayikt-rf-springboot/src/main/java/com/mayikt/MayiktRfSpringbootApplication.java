package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync   //开启Spring异步注解
public class MayiktRfSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MayiktRfSpringbootApplication.class, args);
    }

}
