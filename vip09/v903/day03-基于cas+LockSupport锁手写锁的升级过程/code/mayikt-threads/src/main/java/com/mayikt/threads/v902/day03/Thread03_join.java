package com.mayikt.threads.v902.day03;

/**
 * @author 周宇
 * @create 2021-06-09 0:16
 * 三个线程 T1，T2，T3，怎么确保它们按顺序执行
 * join底层就是通过wait notify进行封装的
 */
public class Thread03_join {
    public static void main(String[] args) {
        //cpu随机调度
        //Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ",线程执行"), "t1");
        //Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ",线程执行"), "t2");
        //Thread t3 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ",线程执行"), "t3");
        //t1.start();
        //t2.start();
        //t3.start();

        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName()+",线程执行");
        },"t1");

        Thread t2 = new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName()+",线程执行");
        },"t2");

        Thread t3 = new Thread(()->{
            try {
                t2.join();
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName()+",线程执行");
        },"t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
