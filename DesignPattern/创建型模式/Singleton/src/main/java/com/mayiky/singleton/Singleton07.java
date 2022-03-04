package com.mayiky.singleton;

/**
 * @author 周宇
 * @create 2021-07-27 17:32
 * 静态内部类
 */
public class Singleton07 {

    private Singleton07() {
        System.out.println(">>>Singleton07");
    }

    //静态的 只会被加载一次 保证了线程安全
    private static class SingletonHolder{
        private static final Singleton07 singleton07 = new Singleton07();
    }

    //静态内部类特征：集成了懒汉式和饿汉式的优点，同时解决了双重检验锁第一次加载慢的问题
    public static final Singleton07 getInstance(){
        return SingletonHolder.singleton07;
    }

    public static void main(String[] args) {
        Singleton07 instance1 = Singleton07.getInstance();
        Singleton07 instance2 = Singleton07.getInstance();
        System.out.println(instance1==instance2);
    }

}
