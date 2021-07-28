package com.mayiky.onservable.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author 周宇
 * @create 2021-07-28 18:19
 */
public class SmsObServer implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("用户下单成功，发送短信提醒内容："+arg);
    }
}
