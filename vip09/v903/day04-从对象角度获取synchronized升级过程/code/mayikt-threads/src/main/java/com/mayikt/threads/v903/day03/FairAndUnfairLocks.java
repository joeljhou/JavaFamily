package com.mayikt.threads.v903.day03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周宇
 * @create 2021-07-01 21:44
 * 公平锁与非公平锁
 */
public class FairAndUnfairLocks implements Runnable {

    private static int count = 0;
    /**
     * 使用给定的公平策略创建
     * true:公平的排序策略
     * false:非公平的排序策略
     */
    private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (count <= 200) {
            lock.lock();
            try {
                if (count <= 200){
                    System.out.println(Thread.currentThread().getName() + ",count:" + count++);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void createCount() {
        count++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new FairAndUnfairLocks()).start();
        }
    }

}
