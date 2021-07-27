package com.mayiky.singleton;

/**
 * @author 周宇
 * @create 2021-07-27 17:12
 * 懒汉式线程不安全
 */
public class Singleton01 {

    private static Singleton01 singleton01;

    /**
     * 私有构造函数
     */
    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        if (singleton01 == null) {
            try {
                //加一个休眠 做不安全测试
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            singleton01 = new Singleton01();
        }
        return singleton01;
    }

    public static void main(String[] args) {
        //单线程测试线程是安全的
        //Test01();

        //多线程测试线程安全问题
        Test02();
    }

    private static void Test01() {
        //单线程测试
        Singleton01 instance1 = Singleton01.getInstance();
        Singleton01 instance2 = Singleton01.getInstance();
        System.out.println(instance1 == instance2);
    }

    private static void Test02() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Singleton01 singleton01 = Singleton01.getInstance();
                System.out.println(Thread.currentThread().getName() + "," + singleton01);
            }).start();
        }
    }

}
