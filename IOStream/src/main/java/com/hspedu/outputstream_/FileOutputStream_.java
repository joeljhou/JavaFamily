package com.hspedu.outputstream_;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream_ {
    /**
     * 演示使用FileOutputStream 将数据写到文件中，
     * 如果文件不存在，则创建该文件
     */
    @Test
    public void writeFile() {
        String filePath = "src\\main\\java\\com\\xhgj\\outputstream_\\a.txt";
        FileOutputStream fileOutputStream = null;
        try {
            //1.创建 FileOutStream 对象
            //new FileOutputStream(filePath)       创建方式会覆盖原来的内容
            //new FileOutputStream(filePath,true)  追加，不是覆盖
            fileOutputStream = new FileOutputStream(filePath, true);
            //写入一个字节
            //fileOutputStream.write('H');
            //写入字符串
            String str = "hsp,world！";
            //fileOutputStream.write(str.getBytes());
            /**
             * write(byte[] b, int off, int len) 将 len字节从位于偏移量 off的指定字节数组写入此文件输出流。
             */
            fileOutputStream.write(str.getBytes(), 0, 3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流释放资源
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
