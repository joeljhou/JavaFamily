package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @EnableFeignClients 可以开启Feign客户端权限
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class AppOrder8010 {

    public static void main(String[] args) {
        SpringApplication.run(AppOrder8010.class, args);
    }

}
