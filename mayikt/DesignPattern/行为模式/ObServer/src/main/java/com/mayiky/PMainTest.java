package com.mayiky;

import com.mayiky.observer.UserObServer;
import com.mayiky.subject.AbstractSubject;
import com.mayiky.subject.impl.WeChatSubject;

/**
 * @author 周宇
 * @create 2021-07-28 17:45
 */
public class PMainTest {
    public static void main(String[] args) {
        //1.创建具体的主题
        AbstractSubject subject = new WeChatSubject();
        //2.开始注册或添加观察者
        subject.addObServer(new UserObServer("张三同学"));
        subject.addObServer(new UserObServer("法外狂徒"));
        //3.群发消息
        subject.setMessage("举起手来！");
    }
}
