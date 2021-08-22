package com.mayikt.service.member.api.impl;

import com.mayikt.service.member.api.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-08-09 2:35
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/")
    public String member(){
        return "会员服务";
    }

    @Override
    public String getUser(Integer userId) {
        return "我是会员服务,端口号为：" + serverPort;
    }
}
