package com.mayikt.threads;

/**
 * @author 周宇
 * @create 2021-06-04 12:38
 */
public class Thread04 {

    /**
     * 共享对象
     */
    class Res{
        /**
         * 姓名
         */
        private String userName;
        /**
         * 性别
         */
        private char sex;
        /**
         * 标记
         */
        private boolean flag = false;
    }

    /**
     * 输入的线程
     */
    class InputThread extends Thread{
        private Res res;
        public InputThread(Res res){
            this.res = res;
        }

        @Override
        public void run() {
            int count = 0;
            while (true){
                synchronized (res){
                    //flag = false  写入输入 flag = true 则不能写入数据只能读取数据
                    try {
                        // 如果flag = true 则不能写入数据只能读取数据同时释放锁!
                        if (res.flag) {
                            res.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(count==0){
                        res.userName = "周宇";
                        res.sex = '男';
                    }else{
                        res.userName="汤金";
                        res.sex = '女';
                    }
                    res.flag = true;
                    res.notify();
                }
                count = (count+1) % 2;
            }

        }
    }

    /**
     * 输出的线程
     */
    class OutputThread extends Thread{
        private Res res;
        public OutputThread(Res res){
            this.res = res;
        }

        @Override
        public void run() {
            while (true){
                synchronized (res){
                    try {
                        if (!res.flag) {
                            res.wait();
                        }
                    } catch (Exception e) {

                    }
                    System.out.println(res.userName + "," + res.sex);
                    res.flag = false;
                    res.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread04().print();
    }

    public void print(){
        //全局对象
        Res res = new Res();
        //输出线程
        InputThread inputThread = new InputThread(res);
        OutputThread outputThread = new OutputThread(res);
        inputThread.start();
        outputThread.start();
    }
}
