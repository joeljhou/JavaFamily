package com.xhgj.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周宇
 * @create 2022-03-10 10:37
 * 基于AQS实现
 */
public class ReentrantLock_ {

    /**
     * 可重入锁 非公平同步(插队)
     * 构造函数 sync = new NonfairSync(); -》 Sync extends AbstractQueuedSynchronizer
     */
    private static ReentrantLock lock = new ReentrantLock();
    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            //开启1000个线程进行累加
            Thread.sleep(1);
            new Thread(() -> {
                /**
                 * 1.通过CAS获取锁
                 * 2.获取锁失败，非公平尝试CAS获取锁 包含重入锁情况
                 * 3.获取锁失败，获取排队 并 设置独占模式线程加入AQS等待队列
                 */
                lock.lock();
                sum++;
                /**
                 * release
                 */
                lock.unlock();  //解锁
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(sum);
    }
}
