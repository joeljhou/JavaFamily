package com.mayikt.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * @author 周宇
 * @create 2021-07-23 23:36
 * Bean后置处理器 配置多个后置处理器 那个先执行
 */
public class MayiktBeanPost02 implements BeanPostProcessor, Ordered {

    /**
     * 调用初始化方法之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【后置处理器02】 Bean Before");
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
        System.out.println("【后置处理器02】 Bean After");
        return bean;
    }

    //值越小，越先执行
    @Override
    public int getOrder() {
        return 2;
    }
}
