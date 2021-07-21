package com.mayikt.test;

import com.mayikt.entity.UserEntity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhouyu
 * @create 2021-05-27 2:00
 */
public class Test02_Constructor {

    /**
     * 有参构造函数
     * 无参构造函数
     */
    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<?> userClass2 = Class.forName("com.mayikt.entity.UserEntity");
        /**
         * 1.默认执行无参构造函数
         */
        UserEntity userEntity1 = (UserEntity) userClass2.newInstance();
        System.out.println(userEntity1);

        /**
         * 2.执行有参构造函数
         */
        Constructor<?> constructor = userClass2.getDeclaredConstructor(String.class, Integer.class);
        UserEntity userEntity2 = (UserEntity) constructor.newInstance("zhouyu", 22);

    }

}
