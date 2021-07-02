package com.mayikt.threads.v903.day02.cas;

/**
 * @author 周宇
 * @create 2021-06-03 10:27
 * 线程安全的问题
 */
public class ThreadCount_MyLock implements Runnable{
    private static int count = 100;
    private static String lock = "lock";
    private static MayiktLock mayiktLock = new MayiktLock();

    /**
     * 如何保证线程一直在运行状态 死循环控制
     */
    @Override
    public void run() {
        while (count > 1) {
            cal();
        }
    }

    /**
     * 1.将synchronized 加在我们的代码块上，对给定对象加锁
     */
    //public void cal(){
    //    synchronized(this){
    //        try {
    //            Thread.sleep(10);
    //        } catch (Exception e) {
    //
    //        }
    //        count--;
    //        System.out.println(Thread.currentThread().getName()+","+count);
    //    }
    //}

    /**
     * 2.将synchronized 加在我们的静态方法上，则使用类名称.class
     */
    //public static void cal(){
    //    synchronized(ThreadCount.class){
    //        try {
    //            Thread.sleep(10);
    //        } catch (Exception e) {
    //
    //        }
    //        count--;
    //        System.out.println(Thread.currentThread().getName()+","+count);
    //    }
    //}

    /**
     * 3.将synchronized 加在我们的实例方法上，则使用this锁
     */
    //public synchronized void cal(){
    //    try {
    //        Thread.sleep(10);
    //    } catch (Exception e) {
    //
    //    }
    //    count--;
    //    System.out.println(Thread.currentThread().getName()+","+count);
    //}

    /**
     * 4.使用MayiktLock 自定义锁
     * 加锁的部分代码相当于单线程执行 保证线程安全
     */
    public void cal(){
        try {
            Thread.sleep(10);
        } catch (Exception e) {

        }
        mayiktLock.lock();
        count--;
        System.out.println(Thread.currentThread().getName()+","+count);
        mayiktLock.unlock();
    }

    public static void main(String[] args) {
        ThreadCount_MyLock threadCount1 = new ThreadCount_MyLock();
        ThreadCount_MyLock threadCount2 = new ThreadCount_MyLock();
        Thread thread1 = new Thread(threadCount1);
        Thread thread2 = new Thread(threadCount2);
        thread1.start();
        thread2.start();
    }
}
