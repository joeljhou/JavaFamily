package com.hspedu.outputstream_;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 周宇
 * @create 2022-02-06 22:51
 * 演示使用BufferedInputStream和BufferedOutputStream
 */
public class BufferedCopy_ {
    @Test
    public void copy() {
        String srcFilePath = "src\\main\\java\\com\\xhgj\\inputstream_\\Java.jpg";       //原文件路径
        String destFilePath = "src\\main\\java\\com\\xhgj\\outputstream_\\Java2.jpg";     //拷贝后文件路径
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.创建BufferedInputStream对象
            bis = new BufferedInputStream(new FileInputStream(srcFilePath));
            //2.创建BufferedOutputStream对象
            bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
            byte[] buf = new byte[16];  //一次读写16个字节
            int bufLen = 0;
            //3.在完成程序时，应该是读取部分数据，就写到指定的文件
            while ((bufLen = bis.read(buf)) != -1) {
                //4.读取到后，就写入文件
                bos.write(buf, 0, bufLen);
            }
            System.out.println("拷贝ok~~~");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流释放资源
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
