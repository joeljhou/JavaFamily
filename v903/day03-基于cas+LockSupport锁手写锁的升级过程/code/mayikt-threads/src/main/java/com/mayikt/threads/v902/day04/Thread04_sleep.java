package com.mayikt.threads.v902.day04;

/**
 * @author 周宇
 * @create 2021-06-19 9:49
 * sleep防止CPU占用100% 只要有休眠的代码 cpu占用率几乎0%
 */
public class Thread04_sleep {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
