package com.mayiky.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author 周宇
 * @create 2021-07-27 17:22
 * 饿汉式（公有）
 */
public class Singleton05 implements Serializable {

    //提前创建单例对象，优点先天性 保证线程安全，比较占用内存
    public static Singleton05 singleton05 = new Singleton05();

    private Singleton05() {
        System.out.println(">>>Singleton05");
    }

    private static Singleton05 getInstance() {
        return singleton05;
    }

    //重写该方法 指定返回的对象 防止序列化破解
    private Object readResolve() throws ObjectStreamException {
        return singleton05;
    }

    public static void main(String[] args) {
        Singleton05 instance1 = Singleton05.singleton05;
        Singleton05 instance2 = Singleton05.singleton05;
        System.out.println(instance1 == instance2);
    }


}
