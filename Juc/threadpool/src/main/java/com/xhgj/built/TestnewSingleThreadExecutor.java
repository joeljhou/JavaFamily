package com.xhgj.built;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 周宇
 * @create 2022-03-05 20:49
 */
public class TestnewSingleThreadExecutor {
    //public ThreadPoolExecutor(int corePoolSize,                       //核心线程数
    //                          int maximumPoolSize,                    //最大线程数
    //                          long keepAliveTime,                     //最大空闲时间
    //                          TimeUnit unit,                          //时间单位
    //                          BlockingQueue<Runnable> workQueue,      //任务队列
    //                          ThreadFactory threadFactory,            //线程工厂
    //                          RejectedExecutionHandler handler){}     //饱和处理机制
    public static void main(String[] args) {
        // 创建一个单线程线程池
        // 核心线程数1 最大线程数1 最大空闲时间0 毫秒 LinkedBlockingQueue
        ExecutorService pool = Executors.newSingleThreadExecutor();

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
