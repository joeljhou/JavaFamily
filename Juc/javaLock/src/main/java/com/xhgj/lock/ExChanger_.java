package com.xhgj.lock;

import java.util.concurrent.Exchanger;

/**
 * @author 周宇
 * @create 2022-03-10 17:37
 */
public class ExChanger_ {

    //一个同步点，线程可以在该点配对和交换对中的元素。
    Exchanger<Integer> exchanger = new Exchanger<>();

    public static void main(String[] args) {

    }
}
