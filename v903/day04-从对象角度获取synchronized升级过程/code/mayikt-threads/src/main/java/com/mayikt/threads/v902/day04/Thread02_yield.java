package com.mayikt.threads.v902.day04;

/**
 * @author 周宇
 * @create 2021-06-10 3:14
 * 线程礼让 主动释放cpu执行权
 */
public class Thread02_yield extends Thread{
    public Thread02_yield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if(i==30){
                System.out.println(Thread.currentThread().getName() + ",释放cpu执行权");
                yield();
            }
            System.out.println(Thread.currentThread().getName() + "," + i);
        }
    }

    public static void main(String[] args) {
        new Thread02_yield("t01").start();
        new Thread02_yield("t02").start();
    }
}
