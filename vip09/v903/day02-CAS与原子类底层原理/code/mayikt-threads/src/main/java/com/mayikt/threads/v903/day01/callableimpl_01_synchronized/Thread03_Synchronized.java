package com.mayikt.threads.v903.day01.callableimpl_01_synchronized;

/**
 * @author 周宇
 * @create 2021-06-20 16:04
 */
public class Thread03_Synchronized {
    public static void main(String[] args) {
        MayiktCallableImpl mayiktCallable = new MayiktCallableImpl();
        MayiktFutureTask<Integer> futureTask = new MayiktFutureTask(mayiktCallable);
        new Thread(futureTask).start();
        Integer result = futureTask.get();
        System.out.println(Thread.currentThread().getName() + result);
    }
}
