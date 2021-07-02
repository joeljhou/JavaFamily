package com.mayikt.threads.v902.day03;

/**
 * @author 周宇
 * @create 2021-06-08 23:40
 * wait/notify/简单的用法 必须结合synchronized
 */
public class Thread01_wait_notify extends Thread{
    @Override
    public void run() {
        try {
            synchronized (this) {
                System.out.println(Thread01_wait_notify.currentThread().getName() + ">>当前线程阻塞，同时释放锁!<<");
                this.wait();
            }
            System.out.println(">>run()<<");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread01_wait_notify thread01 = new Thread01_wait_notify();
        thread01.start();

        try {
            Thread01_wait_notify.sleep(3000);
        } catch (InterruptedException e) {
        }

        synchronized (thread01){
            //唤醒正在阻塞的线程
            thread01.notify();
        }
    }
}
