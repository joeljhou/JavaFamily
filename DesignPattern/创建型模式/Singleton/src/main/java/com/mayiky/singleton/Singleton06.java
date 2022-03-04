package com.mayiky.singleton;

/**
 * @author 周宇
 * @create 2021-07-27 17:27
 * 静态代码块
 */
public class Singleton06 {

    private static Singleton06 singleton06;

    private  Singleton06() {
    }

    static {
        singleton06 = new Singleton06();
    }

    //创建类就创建对象 饿汉式
    public static Singleton06 getInstance(){
        return singleton06;
    }

    public static void main(String[] args) {
        Singleton06 instance1 = Singleton06.getInstance();
        Singleton06 instance2 = Singleton06.getInstance();
        System.out.println(instance1 == instance2);
    }

}
