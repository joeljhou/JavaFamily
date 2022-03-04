package com.mayiky.observer;

/**
 * @author 周宇
 * @create 2021-07-28 17:31
 * 观察者
 */
public interface ObServer {

    /**
     * 通知观察者消息
     * @param message
     */
    void update(String message);

}
