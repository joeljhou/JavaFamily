package com.mayikt.proxy;

import com.mayikt.service.OrderService;
import com.mayikt.service.impl.OrderServiceImpl;

/**
 * @author 周宇
 * @create 2021-07-27 22:19
 * 继承的实现方式
 */
public class OrderServiceProxy_extendsClass extends OrderServiceImpl {

    @Override
    public void order() {
        System.out.println(">>>打印订单日志开始");
        super.order();
        System.out.println(">>>打印订单日志结束");
    }
}
