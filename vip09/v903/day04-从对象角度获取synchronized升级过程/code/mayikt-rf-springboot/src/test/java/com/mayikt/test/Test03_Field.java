package com.mayikt.test;

import com.mayikt.entity.UserEntity;
import java.lang.reflect.Field;

/**
 * @author zhouyu
 * @create 2021-05-27 2:10
 * Field 域
 */
public class Test03_Field {

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<?> userClass2 = Class.forName("com.mayikt.entity.UserEntity");
        UserEntity userEntity = (UserEntity) userClass2.newInstance();
        /**
         * 1.反射为公有属性赋值
         */
        Field pubUserName = userClass2.getDeclaredField("pubUserName");
        pubUserName.set(userEntity,"pub_name");
        System.out.println(userEntity.pubUserName);

        /**
         * 2.反射为私有属性赋值
         */
        Field userName = userClass2.getDeclaredField("userName");
        // 设置允许访问私有属性
        userName.setAccessible(true);
        userName.set(userEntity,"prv_name");
        System.out.println(userEntity.getUserName());

        //遍历域 字段
        //Field[] fields = userClass2.getDeclaredFields();
        //for (Field field : fields) {
        //    System.out.println(field);
        //}
    }

}
