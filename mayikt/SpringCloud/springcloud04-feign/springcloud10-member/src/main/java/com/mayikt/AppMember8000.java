package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//开启eurekaClient客户端 将服务注册到eureak服务端
@EnableEurekaClient
@SpringBootApplication
public class AppMember8000 {

    public static void main(String[] args) {
        SpringApplication.run(AppMember8000.class, args);
    }

}
