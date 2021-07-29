package com.mayiky.controller;

import com.mayiky.factory.FactoryHandler;
import com.mayiky.handler.impl.CurrentLimitHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-29 18:11
 */
@RestController
public class HandlerController {

    @RequestMapping("/clientHandler")
    public String clientHandler(){
        CurrentLimitHandler firstGatewayHandler = FactoryHandler.getFirstGatewayHandler();
        firstGatewayHandler.server();
        return "success";
    }

}
