package com.mayikt.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 周宇
 * @create 2021-06-01 22:02
 * Callable FutrueTask 同步：可以拿到线程返回结果，最终还是变成了单线程
 */
public class ThreadDemo05_CallableFutrueTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 默认代码执行非常耗时!!
        System.out.println(Thread.currentThread().getName() + ",执行计算操作");
        Thread.sleep(3000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadDemo05_CallableFutrueTask threadCallable = new ThreadDemo05_CallableFutrueTask();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(threadCallable);
        new Thread(integerFutureTask).start();
        //我们主线程需要等在子线程给我们返回结果
        Integer result = integerFutureTask.get();
        System.out.println(result);
    }
}
