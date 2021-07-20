package com.mayikt.controller;

import com.mayikt.feign.MemberApiFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 周宇
 * @create 2021-07-17 22:46
 */
@RestController
public class FeignController {

    @Autowired
    private MemberApiFeign memberApiFeign;

    /**
     * 在Spring中有两种方式调用rest,feign(SpringCloud)
     */
    @RequestMapping("/feignMember")
    public String feignMember() {
        return memberApiFeign.getMember();
    }

}
