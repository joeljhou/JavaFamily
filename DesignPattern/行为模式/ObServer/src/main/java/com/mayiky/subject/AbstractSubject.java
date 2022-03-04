package com.mayiky.subject;

import com.mayiky.observer.ObServer;

/**
 * @author 周宇
 * @create 2021-07-28 17:33
 * 抽象主题
 */
public interface AbstractSubject {

    /**
     * 添加观察者 注册观察者
     */
    void addObServer(ObServer obServer);

    /**
     * 移除观察者
     */
    void removeObServer(ObServer obServer);

    /**
     * 设置观察者通知的消息
     */
    void setMessage(String message);

    /**
     * 通知消息
     */
    void notifyObServer();

}
