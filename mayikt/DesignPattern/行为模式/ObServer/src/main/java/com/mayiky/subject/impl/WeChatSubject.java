package com.mayiky.subject.impl;

import com.mayiky.observer.ObServer;
import com.mayiky.subject.AbstractSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2021-07-28 17:37
 * 具体主题
 */
public class WeChatSubject implements AbstractSubject {

    //如何存放这些主题
    private List<ObServer> obServerList = new ArrayList<>();
    /**
     * 设置的消息
     */
    private String message;

    @Override
    public void addObServer(ObServer obServer) {
        //注册或添加观察者
        obServerList.add(obServer);
    }

    @Override
    public void removeObServer(ObServer obServer) {
        obServerList.remove(obServer);
    }

    /**
     * 设置消息并群发广播
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
        notifyObServer();
    }

    @Override
    public void notifyObServer() {
        //调用观察者通知方法 update方法
        obServerList.forEach(o -> o.update(message));
    }
}
