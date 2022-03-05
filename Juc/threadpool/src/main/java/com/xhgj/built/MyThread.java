package com.xhgj.built;

/**
 * @author 周宇
 * @create 2022-03-05 20:47
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 正在运行...");
    }
}
