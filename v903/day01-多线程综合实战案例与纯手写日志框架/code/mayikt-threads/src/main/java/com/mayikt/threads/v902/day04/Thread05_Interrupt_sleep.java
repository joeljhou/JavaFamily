package com.mayikt.threads.v902.day04;

/**
 * @author 周宇
 * @create 2021-06-19 9:56
 * 打断正在阻塞的线程
 */
public class Thread05_Interrupt_sleep {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("打断子线程");
        //调用interrupt 打断正在阻塞的线程
        t1.interrupt();
        System.out.println("获取打断标记：" + t1.isInterrupted());
    }
}
