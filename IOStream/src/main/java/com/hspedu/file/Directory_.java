package com.hspedu.file;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 周宇
 * @create 2022-02-05 19:36
 */
public class Directory_ {
    public static void main(String[] args) {

    }

    //判断 src/main/java/com/xhgj/file/c/newInfo.txt  是否存在，如果存在就删除
    @Test
    public void m1(){
        String filePath = "src/main/java/com/xhgj/file/c/newInfo.txt";
        File file = new File(filePath);
        if (file.exists()){
            //删除
            System.out.println(file.delete()?"删除成功":"删除失败");
        }else{
            System.out.println("不存在");
        }
    }

    //判断 src/main/java/com/xhgj/file/c/ 目录是否存在，存在就删除，否则提示不存在
    @Test
    public void m2(){
        String filePath = "src/main/java/com/xhgj/file/c";
        File file = new File(filePath);
        if (file.isDirectory()) {
            //删除 必须删除目录下的所有文件
            System.out.println(file.delete()?"删除成功":"删除失败");
        }else{
            System.out.println("不存在");
        }
    }

    //判断 src/main/java/com/xhgj/file/c/b 目录是否存在，如果存在就删提示已经存在，否则就创建
    @Test
    public void m3() throws IOException {
        String directoryPath = "src/main/java/com/xhgj/file/c/b";
        File file = new File(directoryPath);
        if (file.isDirectory()) {
            System.out.println("已经存在");
        }else{
            System.out.println(file.mkdirs()?"创建成功":"创建失败");
        }
    }
}
