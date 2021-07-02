package com.mayikt.threads.day004;

/**
 * @author 周宇
 * @create 2021-06-19 9:49
 * sleep防止CPU占用100%
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
