package com.mayikt.entity;

/**
 * @author 周宇
 * @create 2021-07-23 11:17
 */
public class UserEntity {

    private String userName;

    public void addUser(){
        System.out.println("添加用户");
    }

    //执行无参构造方法
    public UserEntity(){
        System.out.println("使用反射初始化，默认走无参构造方法");
    }



}
