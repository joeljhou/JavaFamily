package com.mayikt.test;

import com.mayikt.entity.UserEntity;

/**
 * @author zhouyu
 * @create 2021-05-27 1:42
 * 反射机制 动态获取class 信息内容 类的名称 方法 属性
 */
public class Test01_Class {

    //三种方式动态获取class
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        /**
         * 1.获取class  UserEntity.class
         */
        Class userClass1 = UserEntity.class;
        //默认执行无参的构造函数
        UserEntity userEntity = (UserEntity) userClass1.newInstance();
        System.out.println(userEntity);

        /**
         * 2.获取class Class.forName(“类的完整路径地址 包名+类名”)
         */
        Class<?> userClass2 = Class.forName("com.mayikt.entity.UserEntity");
        System.out.println(userClass1 == userClass2);

        /**
         * 3.获取class new UserEntity().getClass()
         */
        UserEntity userEntity1 = new UserEntity();
        Class userClass3 = userEntity1.getClass();
        System.out.println(userClass1 == userClass3);
    }

}
