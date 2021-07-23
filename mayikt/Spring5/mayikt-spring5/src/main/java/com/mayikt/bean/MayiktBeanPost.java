package com.mayikt.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 周宇
 * @create 2021-07-23 23:36
 * Bean后置处理器
 */
public class MayiktBeanPost implements BeanPostProcessor {

    /**
     * 调用初始化方法之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【后置处理器】 Bean Before");
        return bean;
    }

    /**
     * 调用初始化方法之后执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【后置处理器】 Bean After");
        return bean;
    }


}
