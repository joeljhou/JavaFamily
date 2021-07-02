package com.mayikt.threads.v903.day02.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周宇
 * @create 2021-06-24 13:51
 */
public class MayiktLock {
    /**
     * 默认锁的状态是为=0  如果获取到锁  则锁的状态就是1
     */
    private AtomicInteger loclState = new AtomicInteger(0);
    private Thread cuLockThread;

    /**
     * 获取锁
     */
    public void lock() {
        /**
         * 两个线程同时执行cas操作 将锁的状态从0改为===1
         * 最终只有一个线程修改成功 自旋
         */
        for (; ; ) {
            if (loclState.compareAndSet(0, 1)) {
                return;
            }
            //获取失败，重试获取锁
            try {
                //避免cpu飙高
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        //释放锁 需要通过cas将1==变成0
        for (; ; ) {
            if (loclState.compareAndSet(1, 0)) {
                cuLockThread = null;
                return;
            }
            //获取失败，重试获取锁
            try {
                //避免cpu飙高
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MayiktLock mayiktLock = new MayiktLock();

        Thread t1 = new Thread(() -> {
            mayiktLock.lock();

        });

        t1.start();
        t1.join();

        mayiktLock.lock();
        System.out.println("111");
    }
}
