package com.mayikt.threads.v902.day03;

/**
 * @author 周宇
 * @create 2021-06-09 0:02
 * 多线程通讯实现生产者与消费者
 */
public class Thread02_Producer_Consumer {
    class Res {
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

    class InputThread extends Thread {
        private Res res;

        public InputThread(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            int count = 0;
            while (true) {
                try {
                    synchronized (res) {
                        //flag = false  写入输入 flag = true 则不能写入数据只能读取数据
                        if (res.flag) {
                            res.wait();
                        }

                        if (count == 0) {
                            this.res.userName = "余胜军";
                            this.res.sex = '男';
                        } else {
                            this.res.userName = "小薇";
                            this.res.sex = '女';
                        }
                        res.flag = true;
                        res.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count = (count + 1) % 2;
            }

        }
    }


    class OutThread extends Thread {
        private Res res;

        public OutThread(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (res) {
                    try {
                        if (!res.flag) {
                            res.wait();
                        }
                    } catch (InterruptedException e) {
                    }

                    System.out.println(res.userName + "," + res.sex);
                    res.flag = false;
                    res.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread02_Producer_Consumer().print();
    }

    public void print() {
        Res res = new Res();
        InputThread inputThread = new InputThread(res);
        OutThread outThread = new OutThread(res);
        inputThread.start();
        outThread.start();
    }
}
