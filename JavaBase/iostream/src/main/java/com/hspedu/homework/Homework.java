package com.hspedu.homework;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author 周宇
 * @create 2022-02-07 17:41
 */
public class Homework {
    /**
     * 1.编程题 Homework.java 5min
     * (1)在判断e盘下是否有文件夹mytemp,如果没有就创建mytemp
     * (2)在e:\mytemp目录下，创建文件hello.txt
     * (3)如果hello.txt已经存在，提示该文件已经存在，就不要再重复创建了
     * (4)并且在hello..txt文件中，写入hello,world~
     */
    @Test
    public void homework01() throws IOException {
        //(1)在判断e盘下是否有文件夹mytemp,如果没有就创建mytemp
        String directoryPath = "src/main/java/com/hspedu/homework/mytemp";
        File mytempDirectory = new File(directoryPath);
        if (!mytempDirectory.isDirectory()) {
            //不存在
            mytempDirectory.mkdir();
        }
        File helloFile = new File(mytempDirectory, "hello.txt");
        if (!helloFile.isFile()) {
            //(2)在e:\\nytemp目录下，创建文件hello.txt
            helloFile.createNewFile();
            System.out.println("创建文件hello.txt");
        } else {
            //(3)如果hello..txt已经存在，提示该文件已经存在，就不要再重复创建了
            System.out.println("文件已经存在");
        }
        //(4)并且在hello..txt文件中，写入hello,world~
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(helloFile));
        bufferedWriter.write("hello,world~");
        bufferedWriter.close();
        System.out.println("写入hello,world~");
    }

    /**
     * 要求：使用BufferedReaderi读取一个文本文件，为每行加上行号，
     * 再连同内容一并输出到屏幕上。
     * 加一点难度，文件故意改为GBK编码
     */
    @Test
    public void homework02() throws IOException {
        String filePath = "src/main/java/com/hspedu/homework/Homework.java";
        //BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        //加难度后修改，解决中文乱码
        //转换流InputStreamReader，将FileInputStream转换为InputStreamReader[指定编码]—交给—>BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"gbk"));
        String line = "";
        int lineNum = 1;
        while ((line = bufferedReader.readLine()) != null) {  //循环读取
            System.out.println(lineNum++ + " " + line);
        }
        bufferedReader.close();
    }

    /**
     * 3.编程题Homework03.java
     * (1)要编写一个dog.properties
     * name=tom
     * age=5
     * color=red
     * (2)编写Dog类(name,age,color)创建一个dog对象，读取dog.properties用相应的内容完成属性初始化，并输出
     * (3)将创建的Dog对象，序列化到文件dog.dat文件
     */
    @Test
    public void homework03() throws IOException {
        //(1)要编写一个dog.properties
        Properties properties = new Properties();
        properties.setProperty("name","tom");
        properties.setProperty("age","5");
        properties.setProperty("color","red");
        String filePath = "src/main/java/com/hspedu/homework/dog.properties";
        //处理流-》转换流(字节流转字符流)-》节点流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"utf8"));
        properties.store(bufferedWriter,null);

        //(2)编写Dog类(name,age,color)创建一个dog对象，读取dog.properties用相应的内容完成属性初始化，并输出
        //读取dog.properties
        Properties pread = new Properties();
        pread.load(new BufferedReader(new InputStreamReader(new FileInputStream(filePath))));
        Dog dog = new Dog(pread.getProperty("name"),Integer.valueOf(pread.getProperty("age")),pread.getProperty("color"));

        //(3)将创建的Dog对象，序列化到文件dog.dat文件
        String serializationPath = "src/main/java/com/hspedu/homework/dog.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializationPath));
        oos.writeObject(dog);
        oos.close(); //flush
    }
}