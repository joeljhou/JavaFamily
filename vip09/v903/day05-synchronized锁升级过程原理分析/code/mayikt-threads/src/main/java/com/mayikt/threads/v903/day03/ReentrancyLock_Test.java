package com.mayikt.threads.v903.day03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周宇
 * @create 2021-06-24 23:58
 * 可重入性测试
 */
public class ReentrancyLock_Test {

    private static MayiktLock mayiktLock = new MayiktLock();
    //private static Lock mayiktLock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        /**
         * 锁:
         * 获取锁---
         * 释放锁---
         * synchronized 属于具有可重入性的
         */
        a();
    }

    //public static synchronized void a(){
    //    System.out.println("a");
    //    b();
    //}
    //
    //private static synchronized void b() {
    //    System.out.println("b");
    //    c();
    //}
    //
    //private static synchronized void c() {
    //    System.out.println("c");
    //}

    public static void a() throws Exception {
        mayiktLock.lock();
        System.out.println("a");
        b();
        mayiktLock.unlock();
    }

    private static void b() throws Exception {
        mayiktLock.lock();
        System.out.println("b");
        c();
        mayiktLock.unlock();
    }

    private static void c() throws Exception {
        mayiktLock.lock();
        System.out.println("c");
        mayiktLock.unlock();
    }
}
