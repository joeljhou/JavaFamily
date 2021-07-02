package com.mayikt.service;

import com.mayikt.annotation.MayiktCurrentLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouyu
 * @create 2021-05-27 3:30
 */
@RestController
public class MemberService {

    private String userName;

    /**
     * 每秒生成2.0个令牌
     * //
     */
    //private RateLimiter rateLimiter = RateLimiter.create(2.0);

    @GetMapping("/get")
    @MayiktCurrentLimit(name="get",token=1)
    public String get(){
        //boolean result = rateLimiter.tryAcquire();
        //if (!result) {
        //    return "当前访问人数过多，请稍后重试!";
        //}
        return "my is get";
    }

    @GetMapping("add")
    @MayiktCurrentLimit(name="add",token=5)
    public String add(){
        return "my is add";
    }

    @GetMapping("my")
    public String my(){
        return "my is my";
    }


}
