package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//启用定时任务
@EnableScheduling
@SpringBootApplication
public class Springboot08TimedtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08TimedtaskApplication.class, args);
    }

}
