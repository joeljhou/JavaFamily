package com.mayikt.controller;

import com.mayikt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-24 20:08
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    public String addOrder(int j) {
        int i = orderService.addOrderInfo03(j);
        return i <= 0 ? "fail" : "success";
    }

}
