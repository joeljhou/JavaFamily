package com.hspedu.printstream;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintStream;

/**
 * @author 周宇
 * @create 2022-02-07 14:43
 * 演示PrintStream (字节打印流/输出流)
 */
public class PrintStream_ {
    @Test
    public void printstream() throws IOException {
        PrintStream out = System.out;
        //在默认情况下PrintStream输出数据的位置是标准输出，即显示器
        out.println("打印数据");
        //因为print底层使用write方法
        out.write("北京冬奥会".getBytes());
        //我们可以修改打印流输出的位置/设备
        System.setOut(new PrintStream("src/main/java/com/hspedu/printstream/print.txt"));
        System.out.println("北京冬奥会举办成功了~~~！");
        out.close();
    }
}
