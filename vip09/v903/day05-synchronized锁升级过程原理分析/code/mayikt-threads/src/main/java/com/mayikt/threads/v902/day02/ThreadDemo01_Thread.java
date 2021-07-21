package com.mayikt.threads.v902.day02;

/**
 * @author 周宇
 * @create 2021-06-01 21:54
 * 继承Thread类
 */
public class ThreadDemo01_Thread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"<我是子线程>");
    }

    public static void main(String[] args) {
        // 启动线程是start方法而不是run方法
        System.out.println(Thread.currentThread().getName());
        // 调用start()线程不是立即被cpu调度执行 就绪状态  ----等待cpu调度  线程从 就绪到运行状态
        new ThreadDemo01_Thread().start();
        new ThreadDemo01_Thread().start();
    }
}
