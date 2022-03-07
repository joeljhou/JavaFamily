package com.hspedu.transformation;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 周宇
 * @create 2022-02-07 13:07
 * 演示使用 InputStreamReader 转换流解决中文乱码问题
 * 将字节流FileInputStream转换成字符流InoutStreamReader，指定gbk/utf-8
 */
public class InputStreamReader_ {
    @Test
    public void transformation() {
        String filePath = "src\\main\\java\\com\\hspedu\\transformation\\a.txt";
        //1.把 FileInputStream 转成 InputStreamReader
        //2.指定编码 gbk
        BufferedReader bw = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "gbk");
            //3.把 InputStreamReader 传入BufferedWriter
            bw = new BufferedReader(isr);  //使用try-with-catch,关闭外层流,自动关闭isr流
            String line = bw.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
