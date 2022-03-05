package com.xhgj.built;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 周宇
 * @create 2022-03-05 21:03
 */
public class TestnewFixedThreadPool {
    public static void main(String[] args) {
        // 创建一个固定大小线程池
        // 核心线程数n 最大线程数n 最大空闲时间0 毫秒 LinkedBlockingQueue
        ExecutorService pool = Executors.newFixedThreadPool(2);

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();

        //将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        //关闭线程池
        pool.shutdown();
    }
}
