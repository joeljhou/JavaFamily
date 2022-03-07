package com.hspedu.transformation;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-07 12:33
 */
public class CodeQuestion {
    public static void main(String[] args) {

    }

    @Test
    public void transformation(){
        String filePath = "src\\main\\java\\com\\hspedu\\transformation\\a.txt";
        BufferedReader bufferedReader = null;
        try {
            //1.创建一个BufferedReader对象，使用FileReader文件字符输入流处理
            bufferedReader = new BufferedReader(new FileReader(filePath));
            //2.读取一行 & 打印
            //默认情况下，读取文件是按照utf-8
            //编码使用ansi->gbk 国标码会出现乱码beijing����
            String line = bufferedReader.readLine();
            System.out.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
