package com.xhgj.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 周宇
 * @create 2022-03-10 16:36
 */
public class CountDownLatch_ {

    /**
     * CountDownLatch的构造函数中的count就是闭锁需要等待的线程数量
     * await()：调用该方法的线程会被阻塞，直到构造方法传入的 N 减到 0 的时候，才能继续往下执行；
     * countDown()：使 CountDownLatch 计数值 减 1；
     */
    public static void main(String[] args) {
        SimulateConcurrency();
        System.out.println("================");
        MultiThreadedSummary();
    }

    //场景1 让多个线程等待：模拟并发，让并发线程一起执行
    public static void SimulateConcurrency() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //准备完毕……运动员都阻塞在这，等待号令
                    countDownLatch.await();
                    String parter = "【" + Thread.currentThread().getName() + "】";
                    System.out.println(parter + "开始执行……");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            Thread.sleep(2000);// 裁判准备发令
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();// 发令枪：执行发令
    }

    //场景2 让单个线程等待：多个线程(任务)完成后，进行汇总合并
    public static void MultiThreadedSummary() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
                    System.out.println("finish" + index + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            countDownLatch.await();// 主线程在阻塞，当计数器==0，就唤醒主线程往下执行。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程:在所有任务运行完成后，进行结果汇总");
    }
}
