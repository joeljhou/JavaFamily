package com.mayiky.controller;

import com.mayiky.factory.FactoryHandler;
import com.mayiky.handler.GatewayHandler;
import com.mayiky.handler.impl.CurrentLimitHandler;
import com.mayiky.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-29 18:11
 */
@RestController
public class HandlerController {

    @Autowired
    private HandlerService handlerService;

    @RequestMapping("/clientHandler")
    public String clientHandler(){
        //工厂模式实现
        CurrentLimitHandler firstGatewayHandler = FactoryHandler.getFirstGatewayHandler();
        firstGatewayHandler.server();

        //基于数据库实现
        GatewayHandler dbFirstGatewayHandler = handlerService.getDbFirstGatewayHandler();
        dbFirstGatewayHandler.server();
        return "success";
    }

}
