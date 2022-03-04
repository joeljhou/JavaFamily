package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MayiktOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MayiktOrderApplication.class, args);
    }


    /**
     * 加上@LoadBalanced 注解就可以实现我们的本地负载均衡
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
