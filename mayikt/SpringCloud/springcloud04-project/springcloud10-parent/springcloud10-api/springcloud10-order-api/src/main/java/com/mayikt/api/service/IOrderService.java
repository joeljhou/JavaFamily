package com.mayikt.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 周宇
 * @create 2021-07-20 22:15
 * 订单服务接口
 */
@FeignClient("app-mayikt-order")
public interface IOrderService {

    //订单服务调用会员服务信息 feign
    @RequestMapping("/getOrderToMember")
    String getOrderToMember(String name);

}
