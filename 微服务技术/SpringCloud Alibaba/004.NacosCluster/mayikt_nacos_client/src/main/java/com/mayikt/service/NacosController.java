package com.mayikt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-08-10 17:26
 */
@RestController
@RefreshScope
public class NacosController {

    @Value("${mayikt.name}")
    private String userName;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getConfig")
    public String getConfig() {
        return userName + "，端口；" + serverPort;
    }

}
