package com.mayikt.threads.v903.day02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 周宇
 * @create 2021-06-23 11:17
 * atomicInteger计数: 通过Atomic原子类方式保证线程的安全
 */
public class ThreadSafe02_Atomic extends Thread{
    //private static int sum = 0;
    /**
     * 底层基于cas来实现的
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        sum();
    }

    public void sum(){
        for (int i = 0; i < 10000; i++) {
            //sum++;
            atomicInteger.incrementAndGet(); //底层通过cas实现 lock锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafe02_Atomic thread01 = new ThreadSafe02_Atomic();
        ThreadSafe02_Atomic thread02 = new ThreadSafe02_Atomic();
        thread01.start();
        thread02.start();
        thread01.join();
        thread02.join();
        System.out.println(atomicInteger.get());   //小于2w，发生线程安全性问题
    }
}
