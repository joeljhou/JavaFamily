package com.mayikt.threads;

/**
 * @author 周宇
 * @create 2021-06-04 0:01
 */
public class Test01 {

    private static Object objectLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Test01().print();
    }

    public void print() {
        new Thread(()->{
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName()+">1<");
                /**
                 * this.wait()   釋放锁資源  同时当前线程故意阻塞
                 * this.wait()
                 */

                try {
                    //当前线程释放锁，同时释放锁
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+">2<");
            }
        }).start();

        try {
            Thread.sleep(3000);
            //主线程三秒时候唤醒该子线程
            synchronized (objectLock){
                objectLock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
