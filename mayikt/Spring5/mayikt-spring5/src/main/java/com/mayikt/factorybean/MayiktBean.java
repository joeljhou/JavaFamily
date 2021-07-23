package com.mayikt.factorybean;

import com.mayikt.entity.UserEntity;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 周宇
 * @create 2021-07-23 22:29
 */
public class MayiktBean implements FactoryBean {

    /**
     * 定义Factory返回类型
     */
    @Override
    public Object getObject() throws Exception {
        return new UserEntity();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
