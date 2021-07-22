package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//开启网关代理
@EnableEurekaClient
@SpringBootApplication
public class AppZuulGateway {

    public static void main(String[] args) {
        SpringApplication.run(AppZuulGateway.class, args);
    }

}
