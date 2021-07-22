package com.mayikt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-22 9:55
 */
@RestController
@RefreshScope
public class IndexController {

    //http://127.0.0.1:8882/actuator/refresh
    //https://gitee.com/joeljhou/sc-config/edit/master/project_config/config-client-sit.properties
    @Value("${mayikt}")
    private String mayikt;

    @RequestMapping("/getMayiktInfo")
    private String getMayiktInfo() {
        return mayikt;
    }

}
