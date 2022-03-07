package com.hspedu.reader_;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-06 21:55
 */
public class BufferedReader_ {
    @Test
    public void read() {
        String filePath = "src/main/java/com/xhgj/reader_/BufferedReader_.java";
        BufferedReader bufferedReader = null;
        try {
            //1.创建BufferedReader
            bufferedReader = new BufferedReader(new FileReader(filePath));
            //2.读取
            String line;   //按行读取，效率高。如果已达到流的末尾，则为null
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    //关闭流，底层会自动关闭节点流
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
