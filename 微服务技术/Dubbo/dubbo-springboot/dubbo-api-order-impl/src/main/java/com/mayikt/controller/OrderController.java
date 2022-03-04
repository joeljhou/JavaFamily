package com.mayikt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayikt.api.member.IMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-09-15 12:28
 */
@RestController
public class OrderController {
    @Reference
    private IMemberService memberService;

    @RequestMapping("/orderToMember")
    public String orderToMember() {
        return memberService.getUser();
    }
}
