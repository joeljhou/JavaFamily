package com.mayikt.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-12 8:33
 */
@RestController
@EnableAutoConfiguration //根据应用所声明的依赖来对Spring框架进行自动配置
@ComponentScan(basePackages = "com.mayikt.service") //根据定义的扫描路径，把符合扫描规则的类装配到spring容器中
public class HelloWorldService {
    /**
     * @RestController 与 @Controller之间区别
     * 如果在类上加上@RestController 表示修饰该Controller所有的方法返回JSON格式,直接可以编写
     * 相当于在每个方法上加上 @ResponseBody注解，返回json格式
     * @RestController 是我们SpringMVC提供 而不是SpringBoot提供
     * <p>
     * Rest微服务接口开发中 Rest风格 数据传输格式json 协议http协议
     * Controller控制层注解 SpringMVCUrl接口映射 默认情况下返回页面跳转 如果需要返回json格式的情况下需要@ResponseBody注解
     */
    @RequestMapping("/hello")
    public String index(){
        return "Hello World";
    }

    public static void main(String[] args) {
        /**
         * 启动类入口class 默认整合Tomcat容器端口8080
         */
        SpringApplication.run(HelloWorldService.class, args);
    }

}
