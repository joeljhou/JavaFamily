package com.mayikt.service.impl;

import com.mayikt.service.OrderService;

/**
 * @author 周宇
 * @create 2021-07-27 22:10
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public void order() {
        System.out.println("执行订单的业务逻辑");
    }
}
