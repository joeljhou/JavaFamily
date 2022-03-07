package com.hspedu.standard;

import java.util.Scanner;

/**
 * @author 周宇
 * @create 2022-02-07 12:06
 * 标准输入输出流
 */
public class InputAndOutput {
    public static void main(String[] args) {
        //System 类的 public final static InputStream in = null;
        //System.in 编译类型 InputStream
        //System.in 运行类型 BufferedInputStream
        //表示标准输入，键盘
        System.out.println(System.in.getClass());

        //System 类的 public final static PrintStream out = null;
        //System.in 编译类型 PrintStream
        //System.in 运行类型 PrintStream
        //表示标准输出，显示器
        System.out.println(System.out.getClass());

        System.out.println("hello");
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入内容：");
        String next = scanner.next();
        System.out.println("next=" + next);
    }
}
