package com.mayikt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 周宇
 * @create 2021-07-24 11:17
 */
@Configuration
@ComponentScan(basePackages = {"com.mayikt.service","com.mayikt.aop"})
public class MyConfig {

}
