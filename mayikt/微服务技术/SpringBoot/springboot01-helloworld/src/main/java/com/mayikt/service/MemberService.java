package com.mayikt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-12 9:01
 */
@RestController
//加上RestController 表示修饰该Controller所有的方法返回JSON格式
public class MemberService {

    @Value("${mayikt.name}")
    private String name;
    @Value("${mayikt.age}")
    private String age;

    @RequestMapping("/getMember")
    public String getMember(){
        return "每特教育";
    }

    @RequestMapping("/getProperties")
    public String getProperties(){
        return name+"---"+age;
    }

}
