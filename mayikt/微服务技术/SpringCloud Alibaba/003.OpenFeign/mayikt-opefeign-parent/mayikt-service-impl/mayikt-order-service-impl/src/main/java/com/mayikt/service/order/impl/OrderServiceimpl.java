package com.mayikt.service.order.impl;

import com.mayikt.service.order.openfeign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-08-09 2:49
 */
@RestController
public class OrderServiceimpl {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @RequestMapping("/")
    public String member(){
        return "订单服务";
    }

    /**
     * 基于Feign客户端形式实现rpc远程调用
     *
     * @return
     */
    @RequestMapping("/orderFeignToMember")
    public String orderFeignToMember() {
        String result = memberServiceFeign.getUser(1);
        return "订单服务调用会员服务的接口，返回结果：" + result;
    }

}
