package com.mayikt.threads.v903.day04;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 周宇
 * @create 2021-07-07 0:28
 * 偏向锁  启动jvm参数设置：-XX:BiasedLockingStartupDelay=0
 * 升级为轻量级锁 有另一个线程与偏向锁线程竞争
 */
public class Object03_BiaslockUpgradeLightweightlock {

    static class A{

    }

    public static void main(String[] args) {
        A a = new A();
        //从对象锁中获取的是开启了偏向锁 但是没有关联我们偏向锁的线程
        System.out.println("开启了偏向锁，但是偏向锁没有关联偏向锁线程");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        //第一次获取synchronized锁 在A对象头中存放 主线程偏向锁线程id
        synchronized (a){
            //偏向锁 关联偏向锁线程
            System.out.println("开启了偏向锁，偏向锁是给我们的主线程");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }

        //第二次获取synchronized 现在A对象头 获取偏向锁线程id
        synchronized (a){
            //偏向锁 关联偏向锁线程
            System.out.println("开启了偏向锁，偏向锁是给我们的主线程");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }

        //撤销偏向锁
        //有另一个线程与偏向锁线程竞争
        new Thread(() -> {
            synchronized (a){
                //偏向锁 关联偏向锁线程
                System.out.println("子线程升级为轻量级锁");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        }).start();
    }
}
