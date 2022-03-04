package com.mayiky.singleton;

/**
 * @author 周宇
 * @create 2021-07-27 17:20
 * 懒汉式双重检验锁
 */
public class Singleton03 {

    private static Singleton03 singleton03;

    //如何防止被反射破解
    private Singleton03() throws Exception {
        if (singleton03!=null){
            throw new Exception("该对象已经创建");
        }
        System.out.println("无参构造函数");
    }

    //能够保证线程安全，只会创建该单例对象的时候上锁，获取该该单例对象不会上锁，效率比较高。
    public static Singleton03 getInstance() throws Exception {
        if (singleton03 == null) {
            synchronized (Singleton03.class) {
                if (singleton03 == null) {
                    singleton03 = new Singleton03();
                }
            }
        }
        return singleton03;
    }

    public static void main(String[] args) throws Exception {
        Singleton03 instance1 = Singleton03.getInstance();
        Singleton03 instance2 = Singleton03.getInstance();
        System.out.println(instance1==instance2);
    }

}
