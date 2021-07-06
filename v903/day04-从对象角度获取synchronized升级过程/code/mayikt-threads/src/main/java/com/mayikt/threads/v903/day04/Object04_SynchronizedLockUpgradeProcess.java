package com.mayikt.threads.v903.day04;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 周宇
 * @create 2021-07-07 0:51
 * synchronized锁升级过程
 * 无锁 升级 轻量级 重量级锁
 */
public class Object04_SynchronizedLockUpgradeProcess {
    private static Object objectLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        //无锁 升级 轻量级 重量级锁
        //无锁（001）
        System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());

        new Thread(() -> {
            System.out.println("子线程获取锁之前打印头内容信息");   //无锁状态
            System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());

            synchronized (objectLock){
                try {
                    System.out.println("子线程获取到 对象头信息内容");  //轻量级锁状态
                    System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
                    Thread.sleep(5000);
                    System.out.println("..子线程..");
                } catch (Exception e) {

                }
            }

            System.out.println("子线程获释放锁之前打印对象头内容信息");
            System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
        },"子线程1").start();

        Thread.sleep(1000);
        sync();
    }

    private static void sync() {
        synchronized (objectLock){
            System.out.println("主线程获取锁 重量级别锁");
            System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
        }
    }


}
