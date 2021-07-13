package com.mayikt.threads.v902.day04;

/**
 * @author 周宇
 * @create 2021-06-19 9:41
 * 设置线程优先级
 */
public class Thread03_Priority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int count = 0;
            while(true){
                System.out.println(Thread.currentThread().getName() + "," + count++);
            }
        },"t1线程");

        Thread t2 = new Thread(() -> {
            int count = 0;
            while(true){
                System.out.println(Thread.currentThread().getName() + "," + count++);
            }
        },"t2线程");

        //设置线程的优先级
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        //启动线程
        t1.start();
        t2.start();
    }
}
