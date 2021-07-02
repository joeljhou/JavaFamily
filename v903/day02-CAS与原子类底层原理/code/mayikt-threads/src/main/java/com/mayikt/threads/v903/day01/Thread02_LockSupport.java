package com.mayikt.threads.v903.day01;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 周宇
 * @create 2021-06-20 17:12
 */
public class Thread02_LockSupport {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-1");
            //阻塞当前线程
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "-2");
        });
        t1.start();

        //主线程3s之后再唤醒 子线程继续向下执行
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(t1);
    }
}
