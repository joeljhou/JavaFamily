package com.mayikt.threads.v902.day02;

/**
 * @author 周宇
 * @create 2021-06-01 22:02
 * 匿名类部类
 */
public class ThreadDemo03_AnonymousInnerClass {
    public static void main(String[] args) {
        //使用匿名内部类创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+",>我是子线程<");
            }
        }).start();
    }
}
