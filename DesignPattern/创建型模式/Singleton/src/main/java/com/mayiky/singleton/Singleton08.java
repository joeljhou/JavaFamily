package com.mayiky.singleton;

/**
 * @author 周宇
 * @create 2021-07-27 17:35
 * 枚举实现单例子
 */
public enum Singleton08 {

    //枚举最安全，不可以被反射也不能被序列化 破解
    INSTANCE;

    public void add() {
        System.out.println("add方法..");
    }

    public static void main(String[] args) {
        Singleton08 instance1 =  Singleton08.INSTANCE;
        Singleton08 instance2 =  Singleton08.INSTANCE;
        System.out.println(instance1==instance2);
        instance1.add();
    }

}
