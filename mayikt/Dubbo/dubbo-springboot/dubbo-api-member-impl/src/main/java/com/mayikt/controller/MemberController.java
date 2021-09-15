package com.mayikt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayikt.api.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-09-15 12:22
 */
@RestController
public class MemberController {

    //@Reference
    @Autowired
    private IMemberService memberService;

     @RequestMapping("/member")
     public String member() {
         return memberService.getUser();
     }

}
