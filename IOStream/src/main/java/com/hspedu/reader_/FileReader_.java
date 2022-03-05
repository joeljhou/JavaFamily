package com.hspedu.reader_;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-06 19:08
 */
public class FileReader_ {
    /**
     * 单个字符读取文件
     */
    @Test
    public void readFile01() {
        String filePath = "src\\main\\java\\com\\xhgj\\filereader_\\story.txt";
        FileReader fileReader = null;
        int data;
        try {
            //1.创建FileReader对象
            fileReader = new FileReader(filePath);
            //2.循环读取字符
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符数组读取文件
     */
    @Test
    public void readFile02() {
        String filePath = "src\\main\\java\\com\\xhgj\\reade r_\\story.txt";
        FileReader fileReader = null;
        char[] buf = new char[16];
        int bufLen = 0;
        try {
            //1.创建FileReader对象
            fileReader = new FileReader(filePath);
            //2.循环读取字符
            while ((bufLen = fileReader.read(buf)) != -1) {
                System.out.print(new String(buf, 0, bufLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
