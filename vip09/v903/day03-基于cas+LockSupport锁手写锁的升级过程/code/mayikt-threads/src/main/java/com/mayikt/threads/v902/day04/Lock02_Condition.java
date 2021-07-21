package com.mayikt.threads.v902.day04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周宇
 * @create 2021-06-19 10:26
 * 使用Condition实现等待/通知类似于 wait()和notify()及notifyAll()
 */
public class Lock02_Condition {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Lock02_Condition lock02 = new Lock02_Condition();

        try {
            lock02.print();
            Thread.sleep(3000);
            lock02.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void print() {
        new Thread(() -> {
            //释放锁，同时当前线程阻塞
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+",1");
                condition.await();
                System.out.println(Thread.currentThread().getName()+",2");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
    }

    private void signal() {
        lock.lock();
        try {
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
