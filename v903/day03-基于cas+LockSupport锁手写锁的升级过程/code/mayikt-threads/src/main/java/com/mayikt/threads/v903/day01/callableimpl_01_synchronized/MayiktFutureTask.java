package com.mayikt.threads.v903.day01.callableimpl_01_synchronized;

/**
 * @author 周宇
 * @create 2021-06-20 15:23
 */
public class MayiktFutureTask<V> implements Runnable {


    private MayiktCallable<V> mayiktCallable;
    private Thread cuThread;
    private V result;
    private Object lock = new Object();

    public MayiktFutureTask(MayiktCallable<V> mayiktCallable) {
        this.mayiktCallable = mayiktCallable;
    }

    @Override
    public void run() {
        // 获取到mayiktCallable 执行返回结果
        result = mayiktCallable.call();
        // 如果 call方法执行完毕 则唤醒当前阻塞的线程
        if (cuThread!=null){
            synchronized (lock){
                lock.notify();
            }
        }
    }

    public V get(){
        cuThread = Thread.currentThread();
        synchronized (lock){
            try {
                // 当前线程释放锁 变为阻塞状态
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
