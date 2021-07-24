package com.mayikt.service;

import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-24 11:16
 */
@Component
public class OrderService {
    public String addOrder(){
        System.out.println("addOrder");
        return "addOrder success";
    }
}
