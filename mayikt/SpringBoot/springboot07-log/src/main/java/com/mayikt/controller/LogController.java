package com.mayikt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-14 22:06
 */
@RestController
public class LogController {

    @RequestMapping("/logInfo")
    public String logInfo() {
        return "success";
    }

}
