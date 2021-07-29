package com.mayiky.handler;

import com.mayiky.handler.impl.ConversationHandler;

/**
 * @author 周宇
 * @create 2021-07-29 17:48
 */
public abstract class GatewayHandler {

    /**
     * 下一个Handler
     */
    protected GatewayHandler nextGatewayHandler;

    /**
     * 使用抽象类定义共同方法的行为
     */
    public abstract void server();

    /**
     * 执行下一个handler
     */
    protected void nextServer(){
        if (nextGatewayHandler!=null){
            nextGatewayHandler.server();
        }
    }

    /**
     * 设置下一个GatewayHandler
     * @param nextGatewayHandler
     */
    public void setNextGatewayHandler(GatewayHandler nextGatewayHandler) {
        this.nextGatewayHandler = nextGatewayHandler;
    }

}
