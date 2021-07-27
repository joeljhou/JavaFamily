package com.mayikt;

import com.mayikt.jdk.proxy.JdkInvocationHandler;
import com.mayikt.proxy.OrderServiceProxy_extendsClass;
import com.mayikt.proxy.OrderServiceProxy_impIinterface;
import com.mayikt.service.OrderService;
import com.mayikt.service.impl.OrderServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author 周宇
 * @create 2021-07-27 22:18
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        //1.基于接口实现方式
        OrderServiceProxy_impIinterface orderServiceProxyImpl = new OrderServiceProxy_impIinterface(new OrderServiceImpl());
        orderServiceProxyImpl.order();
        System.out.println("==================== 1.基于接口实现方式");

        //2.基于继承的实现方式
        OrderServiceProxy_extendsClass orderServiceProxyExtends = new OrderServiceProxy_extendsClass();
        orderServiceProxyExtends.order();
        System.out.println("==================== 2.基于继承的实现方式");

        //3.使用JDL动态代理
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");  新版本
        JdkInvocationHandler jdkInvocationHandler = new JdkInvocationHandler(new OrderServiceImpl());
        OrderService orderService1 = jdkInvocationHandler.getProxy();
        orderService1.order();
        System.out.println("==================== 3.使用JDL动态代理");
    }
}
