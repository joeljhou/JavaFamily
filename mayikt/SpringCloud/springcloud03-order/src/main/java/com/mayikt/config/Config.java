package com.mayikt.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 周宇
 * @create 2021-07-17 23:09
 */
@Configuration
public class Config {

    /**
     * 如果使用rest方式以别名方式进行调用 依赖ribbon负载均衡器 @LoadBalanced
     * //就是让restTemplate在请求时拥有负载均衡的能力
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
