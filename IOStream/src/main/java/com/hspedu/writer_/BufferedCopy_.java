package com.hspedu.writer_;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 周宇
 * @create 2022-02-06 22:12
 */
public class BufferedCopy_ {
    public static void main(String[] args) {

    }

    /**
     * 说明
     * 1.BufferedReader 和 BufferedWriter 是按照字符操作
     * 2.不要去炒作 二进制文件[声音，视频，pdf，word文档] 可能照成文件损坏
     */
    @Test
    public void copy() {
        String srcFilePath = "src\\main\\java\\com\\xhgj\\reader_\\story.txt";
        String destFilePath = "src\\main\\java\\com\\xhgj\\writer_\\story.txt";

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            //1.创建BufferedReader对象
            br = new BufferedReader(new FileReader(srcFilePath));
            //2.创建BufferedWriter对象
            bw = new BufferedWriter(new FileWriter(destFilePath));
            //3.读取
            String line = null;
            //按行读取 readLine只读取了一行内容，但是没有换行符
            while ((line = br.readLine()) != null) {
                bw.write(line);
                //插入一个换行符
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
