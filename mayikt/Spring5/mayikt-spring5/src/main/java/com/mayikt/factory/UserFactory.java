package com.mayikt.factory;

import com.mayikt.entity.UserEntity;
import com.mayikt.utils.Dom4jClass;
import org.dom4j.DocumentException;

import java.io.IOException;

/**
 * @author 周宇
 * @create 2021-07-23 11:54
 */
public class UserFactory {

    public static UserEntity getUserEntity() throws DocumentException, IOException {
        //1.使用解析xml技术 解析spring.xml 配置文件 dom4j
        String userClass = new Dom4jClass().getUserClass();
        //2.获取 <bean id="" class=""></bean> 类完整路径地址
        String classUrl = userClass;
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
