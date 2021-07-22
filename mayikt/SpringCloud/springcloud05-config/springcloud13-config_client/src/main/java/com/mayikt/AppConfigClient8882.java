package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AppConfigClient8882 {

    /**
     * 配置信息保存在：https://gitee.com/joeljhou/sc-config <br>
     * 关于SpringCloud中对配置文件bootstrap报错解决方法 <br>
     * No spring.config.import property has been defined <br>
     * 在项目maven中引入 <br>
     * <dependency>
     *      <groupId>org.springframework.cloud</groupId>
     *      <artifactId>spring-cloud-starter-bootstrap</artifactId>
     * </dependency>
     */
    public static void main(String[] args) {
        SpringApplication.run(AppConfigClient8882.class, args);
    }
}
