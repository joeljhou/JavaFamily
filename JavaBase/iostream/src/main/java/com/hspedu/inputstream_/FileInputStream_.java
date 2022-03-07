package com.hspedu.inputstream_;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-06 16:05
 * 演示FileinputStream使用(字节输入流 文件-->程序)
 * 单个字节的读取，效率比较低
 * --> 使用 read(byte[] b)
 */
public class FileInputStream_ {
    @Test
    public void readFile01() {
        String filePath = "src\\main\\java\\com\\xhgj\\inputstream_\\hello.txt";
        int readData = 0;
        FileInputStream fileInputStream = null;
        try {
            //1.创建FileInputStream对象，用于读取文件
            fileInputStream = new FileInputStream(filePath);
            //2.从输入流读取一个字节的数据 如果达到文件的末尾返回-1
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData);  //转成char显示
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流释放资源
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用read(byte[] b)读取文件，提高效率
     */
    @Test
    public void readFile02() {
        String filePath = "src\\main\\java\\com\\xhgj\\inputstream\\hello.txt";
        //定义字节数组
        byte[] buf = new byte[8];  //一次读取8个字节
        int readLen = 0;
        FileInputStream fileInputStream = null;
        try {
            //1.创建FileInputStream对象，用于读取文件
            fileInputStream = new FileInputStream(filePath);
            //2.从输入流读取一个字节的数据 如果达到文件的末尾返回-1
            //如果读取正常，返回实际读取的字节数
            while ((readLen = fileInputStream.read(buf)) != -1) {
                System.out.print(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
