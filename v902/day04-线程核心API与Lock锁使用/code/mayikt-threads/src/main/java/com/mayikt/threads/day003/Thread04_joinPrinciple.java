package com.mayikt.threads.day003;

/**
 * @author 周宇
 * @create 2021-06-10 2:11
 * Join的底层原理如何实现
 * Join底层原理是基于wait封装的，唤醒的代码在jvm Hotspot 源码中当
 * jvm在关闭线程之前会检测线阻塞在t1线程对象上的线程，然后执行notfyAll(),这样t2就被唤醒了。
 */
public class Thread04_joinPrinciple {

    private Object object = new Object();

    public static void main(String[] args) {
        Thread04_joinPrinciple thread04 = new Thread04_joinPrinciple();
        Thread thread = thread04.print();
        thread.start();

        try {
            Thread.sleep(3000);
            // 中断线程
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Thread print(){
        Thread thread = new Thread(() -> {
            synchronized (object){
                System.out.println("1");
                try {
                    object.wait(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        });
        return thread;
    }
}
