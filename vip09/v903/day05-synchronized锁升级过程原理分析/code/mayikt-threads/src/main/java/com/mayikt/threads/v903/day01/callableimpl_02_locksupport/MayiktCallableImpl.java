package com.mayikt.threads.v903.day01.callableimpl_02_locksupport;

/**
 * @author 周宇
 * @create 2021-06-20 16:34
 */
public class MayiktCallableImpl implements MayiktCallable<Integer>{

    @Override
    public Integer call() {
        try {
            System.out.println(Thread.currentThread().getName()+",子线程在执行耗时的代码");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //耗时代码执行完毕后返回1
        return 1;
    }
}
