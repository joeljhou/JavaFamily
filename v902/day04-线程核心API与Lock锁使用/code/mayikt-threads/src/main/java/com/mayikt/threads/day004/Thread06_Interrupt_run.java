package com.mayikt.threads.day004;

/**
 * @author 周宇
 * @create 2021-06-19 9:56
 * 打断正在运行的线程
 */
public class Thread06_Interrupt_run extends Thread{

    @Override
    public void run() {
        while (true){
            //如果终止了线程,则停止当前线程
            if (this.isInterrupted()){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Thread06_Interrupt_run thread06 = new Thread06_Interrupt_run();
        thread06.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread06.interrupt();
    }
}
