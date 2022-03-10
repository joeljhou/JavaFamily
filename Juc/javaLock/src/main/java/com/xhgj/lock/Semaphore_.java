package com.xhgj.lock;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author 周宇
 * @create 2022-03-10 17:02
 * 信号量：控制同时访问特定资源的线程数量，可以用来限流
 * eh:停车场场景，车位数量有限; 数据库连接池，同时进行连接的线程有数量限制
 */
public class Semaphore_ {

    //停车场同时容纳的车辆10
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        //模拟100辆车进入停车场
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                    if (semaphore.availablePermits() == 0) {
                        System.out.println("车位不足，请耐心等待");
                    }
                    semaphore.acquire();//获取令牌尝试进入停车场
                    System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                    Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                    System.out.println(Thread.currentThread().getName() + "驶出停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放令牌，腾出停车场车位
                }
            }, i + "号车");
            thread.start();
        }
    }
}
