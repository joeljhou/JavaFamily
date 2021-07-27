package com.mayikt.proxy;

import com.mayikt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 周宇
 * @create 2021-07-27 22:14
 * 实现接口的方式
 */
public class OrderServiceProxy_impIinterface implements OrderService {

    /**
     * 被代理对象
     */
    private OrderService orderService;

    public OrderServiceProxy_impIinterface(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void order() {
        System.out.println(">>>打印订单日志开始");
        orderService.order();
        System.out.println(">>>打印订单日志结束");
    }
}
