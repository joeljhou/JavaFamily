package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启eurekaServer服务 开启注册中心
@EnableEurekaServer
@SpringBootApplication
public class SpringcloudEureakServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEureakServerApplication.class, args);
    }

}
