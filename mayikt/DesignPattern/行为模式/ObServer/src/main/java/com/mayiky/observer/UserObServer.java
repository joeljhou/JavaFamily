package com.mayiky.observer;

/**
 * @author 周宇
 * @create 2021-07-28 17:43
 */
public class UserObServer implements ObServer {

    private String name;

    public UserObServer(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "，收到微信公众消息：" + message);
    }
}
