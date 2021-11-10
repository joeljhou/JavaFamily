package com.mayikt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-14 0:05
 * //01. @Value注解方式加载配置文件
 * 单个属性单独配置 使用@Value注解
 */
@RestController
public class T01_ValueController {

    @Value("${mayikt.name}")
    private String name;

    @Value("${mayikt.age}")
    private Integer age;

    @RequestMapping("/getPropertiesInfo")
    public String getPropertiesInfo() {
        return name + "," + age;
    }

}
