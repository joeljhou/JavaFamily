package com.mayikt.threads.v902.day04;

/**
 * @author 周宇
 * @create 2021-06-10 3:08
 * 守护线程与用户线程
 */
public class Thread01_Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+",我是子线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * 1.setDaemon 设置为true 守护线程是依赖于用户线程，用户线程退出了，守护线程也就会退出，典型的守护线程如垃圾回收线程。
         * 2.setDaemon 设置为false 用户线程是独立存在的，不会因为其他用户线程退出而退出。
         */
        //thread.setDaemon(true);
        thread.start();
        System.out.println("我是主线程，代码执行结束");
    }
}
