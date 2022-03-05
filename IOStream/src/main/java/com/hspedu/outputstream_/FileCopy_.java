package com.hspedu.outputstream_;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 周宇
 * @create 2022-02-06 16:57
 */
public class FileCopy_ {
    //完成文件拷贝 将inputstream下的Java.jpg拷贝到outputstream下面
    @Test
    public void copy() {
        String srcFilePath = "src\\main\\java\\com\\xhgj\\inputstream_\\Java.jpg";       //原文件路径
        String destFilePath = "src\\main\\java\\com\\xhgj\\outputstream_\\Java.jpg";     //拷贝后文件路径
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            //1.创建文件输入流，将文件读到程序
            fileInputStream = new FileInputStream(srcFilePath);
            //2.创建文件输出流，将读取到的文件数据，写入到指定位置
            fileOutputStream = new FileOutputStream(destFilePath);
            byte[] buf = new byte[16];  //一次读写16个字节
            int bufLen = 0;
            //3.在完成程序时，应该是读取部分数据，就写到指定的文件
            while ((bufLen = fileInputStream.read(buf)) != -1) {
                //4.读取到后，就写入文件
                fileOutputStream.write(buf, 0, bufLen);
            }
            System.out.println("拷贝ok~~~");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流释放资源
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
