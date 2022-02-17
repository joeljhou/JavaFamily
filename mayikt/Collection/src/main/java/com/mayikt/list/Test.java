package com.mayikt.list;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 周宇
 * @create 2022-02-14 9:49
 * 并发修改异常引出CopyOnWriteArrayList JDK5出现的
 * add 和 remove方法都加入了ReentrantLock 可重入锁
 */
public class Test {

    //定义一个全局ArrayList集合,当两个线程同时修改时>抛出并发修改异常
    //private static List<String> globalList = new ArrayList<>();
    private static CopyOnWriteArrayList<String> globalList = new CopyOnWriteArrayList<String>();

    public static void main(String[] args) {
        //并发修改异常
        new Thread(new ThreadOne()).start();
        new Thread(new ThreadTwo()).start();
    }


    private static class ThreadOne implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                globalList.add("i1:" + i);
                print();
            }
        }
    }

    private static class ThreadTwo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                globalList.add("i2:" + i);
                print();
            }
        }
    }

    //打印数据
    public static void print() {
        for (String global : globalList) {
            System.out.println(global);
        }
    }
}
