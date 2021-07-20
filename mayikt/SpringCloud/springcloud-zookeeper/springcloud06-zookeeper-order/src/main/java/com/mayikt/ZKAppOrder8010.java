package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZKAppOrder8010 {

    public static void main(String[] args) {
        SpringApplication.run(ZKAppOrder8010.class, args);
    }

}
