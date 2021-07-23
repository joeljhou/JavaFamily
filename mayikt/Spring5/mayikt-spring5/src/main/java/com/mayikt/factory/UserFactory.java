package com.mayikt.factory;

import com.mayikt.entity.UserEntity;

/**
 * @author 周宇
 * @create 2021-07-23 11:54
 */
public class UserFactory {

    public static UserEntity getUserEntity(){
        String classUrl = "com.mayikt.entity.UserEntity";
        //3.使用反射技术初始化对象
        UserEntity userEntity = null;
        try {
            Class<?> aClass = Class.forName(classUrl);
            //默认执行无参构造函数
            userEntity = (UserEntity) aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }

}
