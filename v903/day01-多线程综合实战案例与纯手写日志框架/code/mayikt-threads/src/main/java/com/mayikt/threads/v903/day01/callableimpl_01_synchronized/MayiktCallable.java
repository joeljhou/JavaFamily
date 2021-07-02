package com.mayikt.threads.v903.day01.callableimpl_01_synchronized;

/**
 * @author 周宇
 * @create 2021-06-20 15:17
 */
@FunctionalInterface
public interface MayiktCallable<V> {

    /**
     * 当前线程执行完毕返回的结果
     * @return
     * @throws Exception
     */
    V call();

}
