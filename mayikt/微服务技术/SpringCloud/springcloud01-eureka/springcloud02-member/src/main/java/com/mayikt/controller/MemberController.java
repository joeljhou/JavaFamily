package com.mayikt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-17 22:32
 */
@RestController
public class MemberController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getMember")
    public String getMember() {
        return "this is getMember！端口号：" + serverPort;
    }

}
