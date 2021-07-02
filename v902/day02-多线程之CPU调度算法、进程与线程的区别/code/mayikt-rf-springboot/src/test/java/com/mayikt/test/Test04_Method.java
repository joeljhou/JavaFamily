package com.mayikt.test;

import com.mayikt.entity.UserEntity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhouyu
 * @create 2021-05-27 2:53
 * Method 方法
 */
public class Test04_Method {

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> userClass2 = Class.forName("com.mayikt.entity.UserEntity");
        UserEntity userEntity = (UserEntity) userClass2.newInstance();

        /**
         * 1.使用反射调用公共方法
         */
        Method testMethod = userClass2.getDeclaredMethod("testMethod");
        testMethod.invoke(userEntity);

        /**
         * 2.使用反射调用私有方法(带参数，返回值)
         */
        Method sumMethod = userClass2.getDeclaredMethod("sumMethod",Integer.class,Integer.class);
        //设置允许调用私有方法
        sumMethod.setAccessible(true);
        Integer result = (Integer) sumMethod.invoke(userEntity, 1, 2);
        System.out.println(result);

        //遍历 方法
        //Method[] methods = userClass2.getMethods();
        //for (Method method : methods) {
        //    System.out.println(method);
        //}
    }

}

