package com.mayiky.handler.impl;

import com.mayiky.handler.GatewayHandler;

/**
 * @author 周宇
 * @create 2021-07-29 17:53
 */
public class CurrentLimitHandler extends GatewayHandler {

    @Override
    public void server() {
        System.out.println("第一关 >>> API接口限流...");
        this.nextServer();
    }

}
