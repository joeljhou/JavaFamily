package com.mayikt.threads.day004;

/**
 * @author 周宇
 * @create 2021-06-19 10:07
 * 正确的线程中止-标志位
 */
public class Thread07_stop_flag extends Thread{

    private volatile boolean isFlag = true;

    @Override
    public void run() {
        int count = 1;
        while (isFlag){
            System.out.println(Thread.currentThread().getName()+","+count++);
        }
    }

    public static void main(String[] args) {
        Thread07_stop_flag thread07 = new Thread07_stop_flag();
        thread07.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //修改标志位
        thread07.isFlag = false;
    }
}
