package com.hspedu.transformation;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 周宇
 * @create 2022-02-07 13:29
 * 演示 OutputStreamWriter 使用
 * 把 FileOutputStream 字节流转成字符流 OutputStreamWriter
 * 指定处理编码 gbk/utf-8/utf8
 */
public class OutputStreamWriter_ {
    @Test
    public void transformation() {
        String filePath = "src\\main\\java\\com\\hspedu\\transformation\\b.txt";
        //处理流/包装流
        OutputStreamWriter osw = null;
        try {
            //1.把 FileOutputStream 转成 OutputStreamWriter
            osw = new OutputStreamWriter(new FileOutputStream(filePath), "utf8");
            osw.write("hi,北京冬运会举办了！！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
