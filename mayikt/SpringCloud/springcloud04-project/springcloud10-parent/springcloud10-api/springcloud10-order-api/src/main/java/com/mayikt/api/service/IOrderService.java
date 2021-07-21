package com.mayikt.api.service;

import com.mayikt.base.ResponseBase;
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
    @RequestMapping("/orderToMember")
    ResponseBase orderToMember(String name);

    @RequestMapping("/getOrderInfo")
    ResponseBase getOrderInfo();

    //订单服务接口调用会员服务接口 没有解决服务雪崩效应
    @RequestMapping("/orderToMemberUserInfo")
    ResponseBase orderToMemberUserInfo();

    //订单服务接口调用会员服务接口 使用Hystrix解决服务雪崩效应
    @RequestMapping("/orderToMemberUserInfoHystrix")
    ResponseBase orderToMemberUserInfoHystrix();

}
