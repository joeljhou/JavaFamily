package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启eurekaServer服务端 发现其他服务
@EnableEurekaServer
@SpringBootApplication
public class AppEureakService {

    public static void main(String[] args) {
        SpringApplication.run(AppEureakService.class, args);
    }

}
