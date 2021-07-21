package com.mayikt.threads.v903.day04;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 周宇
 * @create 2021-07-07 0:02
 * 对象头中获取HashCode 倒着数25+31
 */
public class Object02_HeaderInfoHashCode {

    private static Object lock = new Object();

    public static void main(String[] args) {
        //loclhashCode 懒加载形式
        int i = lock.hashCode();
        //10进制
        System.out.println(i);
        //16进制
        System.out.println(Integer.toHexString(i));
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

}
