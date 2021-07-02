package com.mayikt.test;

import com.mayikt.entity.UserEntity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyu
 * @create 2021-05-27 3:13
 * 越过泛型检查
 */
public class Test05_OverGenericCheck {

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("mayikt");
        //strings.add(1);   泛型检查在编译期

        Class<? extends List> aClass = arrayList.getClass();
        Method add = aClass.getDeclaredMethod("add",Object.class);
        Object invoke = add.invoke(arrayList, 1);
        System.out.println(arrayList);

        //导致的问题问题：类型转换异常
        arrayList.forEach(System.out::println);
    }

}
