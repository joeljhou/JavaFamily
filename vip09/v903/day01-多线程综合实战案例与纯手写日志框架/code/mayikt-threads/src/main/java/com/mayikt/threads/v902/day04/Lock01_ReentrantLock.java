package com.mayikt.threads.v902.day04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周宇
 * @create 2021-06-19 10:17
 * 重入锁 使用ReentrantLock实现同步
 * lock()方法:上锁
 * unlock()方法：释放锁
 */
public class Lock01_ReentrantLock implements Runnable {

    private int count = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //获取锁
            lock.lock();
            try {
                if (count > 1) {
                    count--;
                    System.out.println(Thread.currentThread().getName() + "," + count);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Lock01_ReentrantLock lock01 = new Lock01_ReentrantLock();
        Thread t1 = new Thread(lock01);
        Thread t2 = new Thread(lock01);
        t1.start();
        t2.start();
    }
}
