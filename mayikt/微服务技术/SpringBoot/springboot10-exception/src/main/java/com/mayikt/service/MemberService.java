package com.mayikt.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-15 9:39
 */
@RestController
public class MemberService {

    @RequestMapping("/insertUser")
    public int insertUser(int age) {
        int j = 1 / age;
        return j;
    }

}
