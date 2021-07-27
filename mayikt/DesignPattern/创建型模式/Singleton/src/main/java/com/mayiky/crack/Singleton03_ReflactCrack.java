package com.mayiky.crack;

import com.mayiky.singleton.Singleton03;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 周宇
 * @create 2021-07-27 17:53
 * 利用反射破解单例
 */
public class Singleton03_ReflactCrack {
    public static void main(String[] args) throws Exception {
        Singleton03 singleton01 = Singleton03.getInstance();
        Class<?> aClass = singleton01.getClass();
        //获取构造函数
        Constructor<?> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton03 instance02 = (Singleton03) constructor.newInstance();
        System.out.println(singleton01==instance02);
    }
}
