package com.hspedu.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-05 19:08
 */
public class FileInforation {
    public static void main(String[] args) {

    }

    //获取文件的信息
    @Test
    public void info() throws IOException {
        //先创建文件对象
        File file = new File("src/main/java/com/xhgj/file/c/newInfo.txt");
        file.createNewFile();
        //调用响应的方法，得到对应的信息
        System.out.println("文件名字="+file.getName());
        System.out.println("绝对路径="+file.getAbsolutePath());
        System.out.println("父路径名字="+file.getParent());
        System.out.println("文件的长度(字节)="+file.length());
        System.out.println("文件或目录是否存在="+file.exists());
        System.out.println("是否为文件="+file.isFile());
        System.out.println("是否为目录="+file.isDirectory());
    }

}
