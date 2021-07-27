package com.mayiky.singleton;

/**
 * @author 周宇
 * @create 2021-07-27 17:22
 * 饿汉式（私有）
 */
public class Singleton04 {

    //提前创建单例对象，优点先天性 保证线程安全，比较占用内存
    private static Singleton04 singleton04 = new Singleton04();

    private Singleton04() {
    }

    private static Singleton04 getInstance() {
        return singleton04;
    }

    public static void main(String[] args) {
        Singleton04 instance1 = Singleton04.getInstance();
        Singleton04 instance2 = Singleton04.getInstance();
        System.out.println(instance1 == instance2);
    }


}
