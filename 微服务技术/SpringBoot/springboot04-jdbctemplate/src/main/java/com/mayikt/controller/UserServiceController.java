package com.mayikt.controller;

import com.mayikt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-13 22:01
 */
@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;

    @RequestMapping("/insertUser")
    public String insertUser(String userName, Integer age) {
        return userService.inserUser(userName, age) ? "success" : "fail";
    }

}
