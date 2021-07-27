package com.mayiky.singleton;

/**
 * @author 周宇
 * @create 2021-07-27 17:17
 * 懒汉式线程安全
 */
public class Singleton02 {

    private static Singleton02 singleton02;

    private Singleton02() {
    }

    //该写法能够保证线程安全问题，获取该单例对象的时候效率非常低
    public static synchronized Singleton02 getInstance(){
        if (singleton02==null){
            singleton02 = new Singleton02();
        }
        return singleton02;
    }

    public static void main(String[] args) {
        Singleton02 instance1 = Singleton02.getInstance();
        Singleton02 instance2 = Singleton02.getInstance();
        System.out.println(instance2 == instance1);
    }


}
