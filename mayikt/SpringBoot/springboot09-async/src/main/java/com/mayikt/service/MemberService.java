package com.mayikt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-14 22:38
 */
@Slf4j
@Component
@RestController
public class MemberService {

    @Autowired
    private MemberServiceAsync memberServiceAsync;

    @RequestMapping("/addMember")
    public String addMember() {
        //1.数据库插入数据
        log.info(">01<");
        //2.发送数据
        //smsAsync();
        new Thread(()->memberServiceAsync.smsAsync()).start();
        log.info(">04<");
        return "用户注册成功";
    }

}
