package com.hspedu.printstream;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 周宇
 * @create 2022-02-07 14:54
 * 演示PrintWriter字符输出流
 */
public class PrintWrite_ {
    @Test
    public void transformaction(){
        //PrintWriter printWriter = new PrintWriter(System.out);
        String filePath = "src\\main\\java\\com\\hspedu\\printstream\\c.txt";
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(filePath));
            printWriter.print("hi,北京你好~");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (printWriter!=null){
                //没有close不会flush flush才会将数据写入
                printWriter.close();
            }
        }
    }
}
