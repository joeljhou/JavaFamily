package com.mayikt.controller;

import com.mayikt.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-08-23 1:20
 */
@RestController
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @RequestMapping("/synGatewayConfig")
    public String synGatewayConfig(){
        return gatewayService.loadRoute();
    }

}
