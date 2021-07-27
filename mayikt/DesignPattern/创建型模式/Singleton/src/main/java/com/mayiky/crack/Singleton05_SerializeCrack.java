package com.mayiky.crack;

import com.mayiky.singleton.Singleton05;

import java.io.*;

/**
 * @author 周宇
 * @create 2021-07-27 17:53
 * 利用反射破解单例
 */
public class Singleton05_SerializeCrack implements Serializable {
    public static void main(String[] args) throws Exception {
        //序列化概念：将对象转换成二进制的形式直接存放在本地
        //反序列化概念：从硬盘读取二进制变为对象

        // 1.将对象序列化存入到本地文件中
        FileOutputStream fos = new FileOutputStream("d:/code/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Singleton05 singleton1 = Singleton05.singleton05;
        oos.writeObject(singleton1);
        oos.close();
        fos.close();
        //2.从硬盘中反序列化对象到内存中
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/code/a.txt"));
        Singleton05 singleton2 = (Singleton05) ois.readObject();
        //两个对象不一样 破解了单例
        System.out.println(singleton1);
        System.out.println(singleton2);
    }
}
