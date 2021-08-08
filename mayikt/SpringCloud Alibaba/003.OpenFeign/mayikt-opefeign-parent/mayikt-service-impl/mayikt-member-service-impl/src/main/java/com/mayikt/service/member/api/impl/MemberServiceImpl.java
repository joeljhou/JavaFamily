package com.mayikt.service.member.api.impl;

import com.mayikt.service.member.api.MemberService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-08-09 2:35
 */
@RestController
public class MemberServiceImpl implements MemberService {
    @Override
    public String getUser(Integer userId) {
        return "我是会员服务";
    }
}
