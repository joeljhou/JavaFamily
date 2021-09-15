package com.mayikt.api.member.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mayikt.api.member.IMemberService;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-09-15 12:21
 */
@Service
@Component
public class MemberServiceImpl implements IMemberService {

    //1、dubbo服务发布 采用 注解方式 使用Dubbo提供的 @Service 注解进行发布服务
    //2.Dubbo提供的@Service 将该接口注册到注册中心上去
    //3.Spring提供的@Service将该接口注入到Spring容器中
    @Override
    public String getUser() {
        return "订单服务调用会员服务接口";
    }
}
