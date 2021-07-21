package com.mayikt.threads;

/**
 * @author 周宇
 * @create 2021-06-03 21:17
 * synchronized死锁问题
 */
public class DeadlockThread implements Runnable{

    private int count = 1;
    private String lock = "lock";

    @Override
    public void run() {
        while (true){
            count++;
            if (count % 2 == 0){
                // 线程1需要获取 lock 在获取 a方法this锁
                // 线程2需要获取 this 锁在获取B方法lock锁
                synchronized (lock) {
                    a();
                }
            }else{
                synchronized (this) {
                    b();
                }
            }
        }
    }

    public synchronized void a() {
        System.out.println(Thread.currentThread().getName() + ",a方法...");
    }

    public void b() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ",b方法...");
        }
    }

    public static void main(String[] args) {
        DeadlockThread deadlockThread = new DeadlockThread();
        Thread thread1 = new Thread(deadlockThread);
        Thread thread2 = new Thread(deadlockThread);
        thread1.start();
        thread2.start();
    }
}
