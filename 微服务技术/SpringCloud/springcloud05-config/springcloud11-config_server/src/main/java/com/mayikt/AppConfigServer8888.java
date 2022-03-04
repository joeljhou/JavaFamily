package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//开启config Server服务器端
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class AppConfigServer8888 {

    //如何把我们的配置文件存放在git环境上

    /**
     * 1.在git环境上创建配置文件名称规范
     *      服务名称-环境.properties
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppConfigServer8888.class, args);
    }

}
