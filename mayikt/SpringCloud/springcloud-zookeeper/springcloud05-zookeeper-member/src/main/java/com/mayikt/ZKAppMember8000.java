package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient 注解的作用是 如果服务使用 consul，zookeeper作为注册中心
 * @EnableDiscoveryClient 向注册中心注册服务
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZKAppMember8000 {

    public static void main(String[] args) {
        SpringApplication.run(ZKAppMember8000.class, args);
    }

}
