package com.mayikt.threads.v903.day01.callableimpl_02_locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 周宇
 * @create 2021-06-20 16:39
 */
public class MayiktFutureTask<V> implements Runnable{
    private MayiktCallable<V> mayiktCallable;
    private Thread cuThread;
    private V result;

    public MayiktFutureTask(MayiktCallable<V> mayiktCallable) {
        this.mayiktCallable = mayiktCallable;
    }

    @Override
    public void run() {
        // 获取到mayiktCallable 执行返回结果
        result = mayiktCallable.call();
        // 如果 call方法执行完毕 则唤醒当前阻塞的线程
        if(cuThread != null){
            LockSupport.unpark(cuThread);
        }
    }

    public V get(){
        cuThread = Thread.currentThread();
        // 阻塞当前线程
        LockSupport.park();
        return result;
    }

}
