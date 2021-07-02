package com.mayikt.threads;

import java.util.concurrent.*;

/**
 * @author 周宇
 * @create 2021-06-01 22:02
 * 线程池的方式创建线程
 */
public class ThreadDemo06_Executors {
    public static void main(String[] args) {
        //阿里Java规约禁止使用Java内置Executors创建线程池
        //ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = new ThreadPoolExecutor(1,100,100, TimeUnit.SECONDS,new LinkedBlockingDeque<>(100));
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+">我是子线程<");
            }
        });
    }
}
