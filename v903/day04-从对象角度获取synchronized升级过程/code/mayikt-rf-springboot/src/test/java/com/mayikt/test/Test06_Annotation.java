package com.mayikt.test;

import com.mayikt.annotation.MayiktName;
import com.mayikt.entity.UserEntity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhouyu
 * @create 2021-05-27 3:46
 */
public class Test06_Annotation {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        Class<?> userClass2 = Class.forName("com.mayikt.entity.UserEntity");
        UserEntity userEntity = (UserEntity) userClass2.newInstance();

        // 1.获取当前类上的注解
        MayiktName classAnnotation = userClass2.getDeclaredAnnotation(MayiktName.class);
        System.out.println(classAnnotation);

        // 2.获取当前方法上的注解
        Method testMethod = userClass2.getDeclaredMethod("testMethod");
        MayiktName methodAnnotation = testMethod.getDeclaredAnnotation(MayiktName.class);
        System.out.println(methodAnnotation);

        // 3.获取字段上的注解
        Field pubUserName = userClass2.getDeclaredField("pubUserName");
        MayiktName fieldAnnotation = pubUserName.getDeclaredAnnotation(MayiktName.class);
        System.out.println(fieldAnnotation);

        // 4.获得构造方法注解
        Constructor<?> constructor = userClass2.getDeclaredConstructor(new Class[]{});// 先获得构造方法对象
        MayiktName constructorAnnotation = constructor.getAnnotation(MayiktName.class);         // 拿到构造方法上面的注解实例
        System.out.println(constructorAnnotation);
    }

}
