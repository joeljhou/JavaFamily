package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 搭建Eureak集群，原理就是相互注册
 */
@EnableEurekaServer
@SpringBootApplication
public class AppEureakService8762 {

    public static void main(String[] args) {
        SpringApplication.run(AppEureakService8762.class, args);
    }

}
