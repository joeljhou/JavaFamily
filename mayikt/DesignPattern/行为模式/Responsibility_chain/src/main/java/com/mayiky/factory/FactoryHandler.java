package com.mayiky.factory;

import com.mayiky.handler.impl.BlacklistHandler;
import com.mayiky.handler.impl.ConversationHandler;
import com.mayiky.handler.impl.CurrentLimitHandler;

/**
 * @author 周宇
 * @create 2021-07-29 18:04
 */
public class FactoryHandler {

    /**
     * 工厂模式实现责任链
     */
    public static CurrentLimitHandler getFirstGatewayHandler(){
        //1.第一关
        CurrentLimitHandler currentLimitHandler = new CurrentLimitHandler();
        //2.第二关
        BlacklistHandler blacklistHandler = new BlacklistHandler();
        currentLimitHandler.setNextGatewayHandler(blacklistHandler);
        //3.第三关
        ConversationHandler conversationHandler = new ConversationHandler();
        blacklistHandler.setNextGatewayHandler(conversationHandler);
        return currentLimitHandler;
    }


}
