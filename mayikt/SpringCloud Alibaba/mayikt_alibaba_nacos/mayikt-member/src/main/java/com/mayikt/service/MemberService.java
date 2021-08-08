package com.mayikt.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-08-08 23:05
 */
@RestController
public class MemberService {

    /**
     * 会员服务提供的接口被订单服务调用
     * @param userId
     * @return
     */
    @GetMapping("/getUser")
    public String getUser(Integer userId){
        return "每特教育|蚂蚁课堂";
    }

}
