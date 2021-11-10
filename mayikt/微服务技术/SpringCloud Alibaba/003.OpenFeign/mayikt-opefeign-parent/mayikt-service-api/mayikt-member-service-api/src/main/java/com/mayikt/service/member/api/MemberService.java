package com.mayikt.service.member.api;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 周宇
 * @create 2021-08-09 2:26
 */
public interface MemberService {

    /**
     * 提供发布的接口
     * @param userId
     * @return
     */
    @GetMapping("/getUser")
    String getUser(Integer userId);

}
