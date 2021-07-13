package com.mayikt.threads.v903.day01;

/**
 * @author 周宇
 * @create 2021-06-19 22:17
 * 字节码角度分析线程安全问题-线程安全代码
 * javap -p -v Thread01_Safe.class 反编译位汇编语言 进行分析
 */
public class Thread01_ByteSafe extends Thread{

    public static int sum = 0;

    @Override
    public void run() {
        sum();
    }

    public void sum(){
        for (int i = 0; i < 10000; i++) {
            sum++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread01_ByteSafe t1 = new Thread01_ByteSafe();
        Thread01_ByteSafe t2 = new Thread01_ByteSafe();
        t1.start();
        t2.start();
        //主程序先让t1 t2执行
        t1.join();
        t2.join();
        System.out.println(sum);
    }
}
