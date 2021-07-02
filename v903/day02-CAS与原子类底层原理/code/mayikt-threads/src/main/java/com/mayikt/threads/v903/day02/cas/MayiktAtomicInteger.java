package com.mayikt.threads.v903.day02.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 周宇
 * @create 2021-06-24 0:09
 * 基于Cas实现Atomic原子类
 * //E 旧的预期值
 * //N 要修改的值
 * //V 共享变量的值
 */
public class MayiktAtomicInteger {
    // 设定v=0
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public void incrementAndGet(){
        //Integer v = atomicInteger.get();
        for (;;){
            //一直重试不断循环 如果cas 失败的 则一直不断重试
            Integer expect = atomicInteger.get();
            if (atomicInteger.compareAndSet(expect,expect+1)){
                return;
            }

            try {
                //休眠 防止cpu飙高
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 旧的预期值=expect
         * 新的值=update
         * V = 0
         * cas 算法
         * 旧的预期值：===V  V===N
         */
        //boolean result = atomicInteger.compareAndSet(1, 2);
        //System.out.println(result);
        //System.out.println(atomicInteger.get());

        MayiktAtomicInteger mayiktAtomicInteger = new MayiktAtomicInteger();
        mayiktAtomicInteger.incrementAndGet();
        mayiktAtomicInteger.incrementAndGet();
        mayiktAtomicInteger.incrementAndGet();
        System.out.println(atomicInteger.get());
    }

}
