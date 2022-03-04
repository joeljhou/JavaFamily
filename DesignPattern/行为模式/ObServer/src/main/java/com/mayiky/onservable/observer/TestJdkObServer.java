package com.mayiky.onservable.observer;

import com.mayiky.onservable.MessageObServable;

import java.util.Observable;

/**
 * @author 周宇
 * @create 2021-07-28 18:22
 */
public class TestJdkObServer {
    public static void main(String[] args) {
        //1.创建具体的主题
        Observable observable = new MessageObServable();
        //2.注册观察者
        observable.addObserver(new SmsObServer());
        observable.addObserver(new EmailObServer());
        observable.addObserver(new WeChatObServer());
        //3.群发消息
        observable.notifyObservers("success");
    }
}
