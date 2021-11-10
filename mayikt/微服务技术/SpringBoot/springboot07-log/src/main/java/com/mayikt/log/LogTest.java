package com.mayikt.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-14 0:47
 * 使用logback记录日志
 * Springboot 已经默认帮你整合好了logback 添加lombok依赖
 * 日志输出文件在当前项目路径log文件夹下
 */
@Slf4j
@RestController
public class LogTest {

    /**
     * 演示打印日志
     */
    @RequestMapping("/getName")
    public String getName(String name, int age) {
        log.info("name:{},age:{}", name, age);
        log.debug("");
        try {
            log.error("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

}
