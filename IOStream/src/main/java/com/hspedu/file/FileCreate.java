package com.hspedu.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-05 17:58
 * 演示创建文件
 */
public class FileCreate {
    public static void main(String[] args) {

    }


    //方式1
    @Test
    public void create01() throws IOException {
        String filePath = "src\\main\\java\\com\\xhgj\\file\\c\\news1.txt";
        File file = new File(filePath);
        if (file.createNewFile()){
            System.out.println("创建成功");
        }else{
            System.out.println("创建失败");
        }
    }

    //方式2
    @Test
    public void create02() throws IOException {
        File parentFile = new File("src\\main\\java\\com\\xhgj\\file\\c\\");
        String fileName = "news2.txt";
        File file = new File(parentFile, fileName);
        if (file.createNewFile()){
            System.out.println("创建成功");
        }else{
            System.out.println("创建失败");
        }
    }

    //方式3
    @Test
    public void create03() throws IOException {
        String parentPath = "src\\main\\java\\com\\xhgj\\file\\c\\";
        String fileName = "news3.txt";
        File file = new File(parentPath, fileName);
        if (file.createNewFile()){
            System.out.println("创建成功");
        }else{
            System.out.println("创建失败");
        }
    }
}
