package com.hspedu.homework;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author ����
 * @create 2022-02-07 17:41
 */
public class Homework {
    /**
     * 1.����� Homework.java 5min
     * (1)���ж�e�����Ƿ����ļ���mytemp,���û�оʹ���mytemp
     * (2)��e:\mytempĿ¼�£������ļ�hello.txt
     * (3)���hello.txt�Ѿ����ڣ���ʾ���ļ��Ѿ����ڣ��Ͳ�Ҫ���ظ�������
     * (4)������hello..txt�ļ��У�д��hello,world~
     */
    @Test
    public void homework01() throws IOException {
        //(1)���ж�e�����Ƿ����ļ���mytemp,���û�оʹ���mytemp
        String directoryPath = "src/main/java/com/hspedu/homework/mytemp";
        File mytempDirectory = new File(directoryPath);
        if (!mytempDirectory.isDirectory()) {
            //������
            mytempDirectory.mkdir();
        }
        File helloFile = new File(mytempDirectory, "hello.txt");
        if (!helloFile.isFile()) {
            //(2)��e:\\nytempĿ¼�£������ļ�hello.txt
            helloFile.createNewFile();
            System.out.println("�����ļ�hello.txt");
        } else {
            //(3)���hello..txt�Ѿ����ڣ���ʾ���ļ��Ѿ����ڣ��Ͳ�Ҫ���ظ�������
            System.out.println("�ļ��Ѿ�����");
        }
        //(4)������hello..txt�ļ��У�д��hello,world~
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(helloFile));
        bufferedWriter.write("hello,world~");
        bufferedWriter.close();
        System.out.println("д��hello,world~");
    }

    /**
     * Ҫ��ʹ��BufferedReaderi��ȡһ���ı��ļ���Ϊÿ�м����кţ�
     * ����ͬ����һ���������Ļ�ϡ�
     * ��һ���Ѷȣ��ļ������ΪGBK����
     */
    @Test
    public void homework02() throws IOException {
        String filePath = "src/main/java/com/hspedu/homework/Homework.java";
        //BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        //���ѶȺ��޸ģ������������
        //ת����InputStreamReader����FileInputStreamת��ΪInputStreamReader[ָ������]��������>BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"gbk"));
        String line = "";
        int lineNum = 1;
        while ((line = bufferedReader.readLine()) != null) {  //ѭ����ȡ
            System.out.println(lineNum++ + " " + line);
        }
        bufferedReader.close();
    }

    /**
     * 3.�����Homework03.java
     * (1)Ҫ��дһ��dog.properties
     * name=tom
     * age=5
     * color=red
     * (2)��дDog��(name,age,color)����һ��dog���󣬶�ȡdog.properties����Ӧ������������Գ�ʼ���������
     * (3)��������Dog�������л����ļ�dog.dat�ļ�
     */
    @Test
    public void homework03() throws IOException {
        //(1)Ҫ��дһ��dog.properties
        Properties properties = new Properties();
        properties.setProperty("name","tom");
        properties.setProperty("age","5");
        properties.setProperty("color","red");
        String filePath = "src/main/java/com/hspedu/homework/dog.properties";
        //������-��ת����(�ֽ���ת�ַ���)-���ڵ���
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"utf8"));
        properties.store(bufferedWriter,null);

        //(2)��дDog��(name,age,color)����һ��dog���󣬶�ȡdog.properties����Ӧ������������Գ�ʼ���������
        //��ȡdog.properties
        Properties pread = new Properties();
        pread.load(new BufferedReader(new InputStreamReader(new FileInputStream(filePath))));
        Dog dog = new Dog(pread.getProperty("name"),Integer.valueOf(pread.getProperty("age")),pread.getProperty("color"));

        //(3)��������Dog�������л����ļ�dog.dat�ļ�
        String serializationPath = "src/main/java/com/hspedu/homework/dog.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializationPath));
        oos.writeObject(dog);
        oos.close(); //flush
    }
}