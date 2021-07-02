package com.mayikt.threads.v903.day03;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 周宇
 * @create 2021-06-24 13:51
 * 偏向锁/轻量级锁/重量级锁 Java代码实现
 */
public class MayiktLock {
    /**
     * 默认锁的状态是为=0  如果获取到锁  则锁的状态就是1
     */
    private AtomicInteger loclState = new AtomicInteger(0);

    /**
     * 当前谁持有了该锁
     */
    private Thread ownerLockThread;
    /**
     * 记录锁的重入次数
     */
    private int recursions;
    /**
     * 没有获取到锁的线程
     */
    private LinkedBlockingDeque<Thread> notLockThreadList = new LinkedBlockingDeque<>();

    /**
     * 获取锁
     */
    public void lock() {
        //如果当前线程已经获取到锁的话，则不需要重复获取锁 直接使用 偏向锁
        if (ownerLockThread != null && ownerLockThread == Thread.currentThread()){
            //重入次数+1
            recursions++;
            return;
        }

        /**
         * 两个线程同时执行cas操作 将锁的状态从0改为===1
         * 最终只有一个线程修改成功 自旋
         */
        /**
         * 每个线程获取锁重试次数
         */
        int spinCount = 0;
        for (; ; ) {
            if(spinCount >= 3){
                //当前线程直接阻塞 该过程为重量级锁
                notLockThreadList.offer(Thread.currentThread());
                //阻塞当前线程 -> 重量级锁，唤醒成本高
                LockSupport.park();
                //需要我们重试次数归零
                spinCount = 0;
            }

            //优化代码 当前线程如果已经获取到锁 则直接复用 -> 轻量级锁
            if (loclState.compareAndSet(0, 1)) {
                //当前线程获取锁成功
                ownerLockThread = Thread.currentThread();
                //重入次数+1
                recursions++;
                return;
            }

            // 自旋次数+1
            spinCount++;

            //获取失败，重试获取锁
            try {
                //避免cpu飙高
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        recursions--;
        //释放锁 需要通过cas将1==变成0
        if (recursions==0){
            for (; ; ) {
                if (loclState.compareAndSet(1, 0)) {
                    // 唤醒正在阻塞的线程， 进入到获取锁的状态
                    notifyNotLockThread();
                    return;
                }

                //获取失败，重试获取锁
                try {
                    //避免cpu飙高
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 唤醒没有获取到锁线程  非公平锁的形式
     */
    private void notifyNotLockThread(){
        //唤醒所有 非公平锁实现
        //notLockThreadList.forEach((t)->{
        //    LockSupport.unpark(t);
        //    notLockThreadList.remove(t);
        //});

        //公平锁实现 poll 取出当前队列中第一个并删除
        Thread poll = notLockThreadList.poll();
        LockSupport.unpark(poll);
    }

    public static void main(String[] args) throws Exception {
        cal();
    }

    public static void cal() throws Exception {
        MayiktLock mayiktLock = new MayiktLock();
        //主线程先调用lock方法
        mayiktLock.lock();

        Thread t1 = new Thread(() -> {
            try {
                //轻量级CAS重试
                mayiktLock.lock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mayiktLock.unlock();
            }
        });
        t1.start();

        Thread.sleep(5000);
        mayiktLock.unlock();
    }

}
