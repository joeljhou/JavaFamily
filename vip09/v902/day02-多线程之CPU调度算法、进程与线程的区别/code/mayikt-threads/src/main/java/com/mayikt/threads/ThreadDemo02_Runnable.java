package com.mayikt.threads;

/**
 * @author 周宇
 * @create 2021-06-01 22:02
 * 实现Runable接口
 */
public class ThreadDemo02_Runnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ",我是子线程");
    }

    public static void main(String[] args) {
        new Thread(new ThreadDemo02_Runnable()).start();
    }
}
