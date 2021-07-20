package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulAppMember8000 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulAppMember8000.class, args);
    }

}
