package com.xhgj.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 周宇
 * @create 2022-03-10 17:14
 * 循环栅栏：
 */
public class CyclicBarrier_ {

    /**
     * Semaphore      是一个同步的辅助类，其实和锁有点类似，它一般用于控制对某组资源的访问权限
     * CountDownLatch 是一个同步的辅助类，允许一个或多个线程，等待其他一组线程完成操作，再继续执行。
     * CyclicBarrier  是一个同步的辅助类，允许一组线程相互之间等待，达到一个共同点，再继续执行。
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println(Thread.currentThread().getName() + " 完成最后任务");
        });

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 到达栅栏 A");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " 冲破栅栏 A");

                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " 到达栅栏 B");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " 冲破栅栏 B");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
