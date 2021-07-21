package com.mayikt.threads.v903.day02;

/**
 * @author 周宇
 * @create 2021-06-03 10:27
 * 通过Synchronized方式保证线程的安全
 * Synchronized锁在早期是一个重量级锁，虽然说现在Synchronized有了锁的升级过程，但是没有直接通过CAS来实现，效率不高
 */
public class ThreadSafe01_Synchronized implements Runnable{
    private static int count = 100;
    private static String lock = "lock";

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
    public synchronized void cal(){
        if(count>1){
            count--;
            System.out.println(Thread.currentThread().getName()+","+count);
        }
    }

    public static void main(String[] args) {
        ThreadSafe01_Synchronized threadCount1 = new ThreadSafe01_Synchronized();
        new Thread(threadCount1).start();
        new Thread(threadCount1).start();
    }
}
