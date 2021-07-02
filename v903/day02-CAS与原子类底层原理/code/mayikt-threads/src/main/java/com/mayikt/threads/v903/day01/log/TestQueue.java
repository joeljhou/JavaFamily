package com.mayikt.threads.v903.day01.log;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author 周宇
 * @create 2021-06-21 13:15
 */
public class TestQueue {
    public static void main(String[] args) {
        //无界队列 无限存放内容
        LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
        //添加元素
        blockingDeque.offer("log1");
        blockingDeque.offer("log2");

        //返回第一个元素
        System.out.println("===");
        System.out.println(blockingDeque.element());

        //返回第一个元素
        System.out.println("===");
        System.out.println(blockingDeque.peek());

        //从队列中取出日志  返回第一个元素，并在队列中删除
        System.out.println("===");
        System.out.println(blockingDeque.poll());
        System.out.println(blockingDeque.poll());
        System.out.println(blockingDeque.poll());
    }
}
