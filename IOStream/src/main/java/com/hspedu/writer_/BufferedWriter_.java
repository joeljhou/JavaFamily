package com.hspedu.writer_;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-06 22:06
 */
public class BufferedWriter_ {
    @Test
    public void write() {
        String filePath = "src/main/java/com/xhgj/writer_/ok.txt";
        BufferedWriter bufferedWriter = null;

        try {
            //1.创建BufferedWriter对象
            bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            //2.写入
            bufferedWriter.write("hello,中国！");
            bufferedWriter.newLine();//插入一个和系统相关的换行符
            bufferedWriter.write("hello,湖南！");
            bufferedWriter.write("hello,岳阳！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
