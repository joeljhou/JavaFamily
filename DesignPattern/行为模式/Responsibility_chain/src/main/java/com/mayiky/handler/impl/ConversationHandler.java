package com.mayiky.handler.impl;

import com.mayiky.handler.GatewayHandler;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-29 17:53
 */
@Component
public class ConversationHandler extends GatewayHandler {

    @Override
    public void server() {
        System.out.println("第三关 >>> 用户会话信息拦截...");
        this.nextServer();
    }
}
