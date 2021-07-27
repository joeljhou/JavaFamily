package com.mayikt;

import com.mayikt.proxy.OrderServiceProxy_extendsClass;
import com.mayikt.proxy.OrderServiceProxy_impIinterface;
import com.mayikt.service.impl.OrderServiceImpl;

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

        //2.基于继承的实现方式
        OrderServiceProxy_extendsClass orderServiceProxyExtends = new OrderServiceProxy_extendsClass();
        orderServiceProxyExtends.order();
    }
}
