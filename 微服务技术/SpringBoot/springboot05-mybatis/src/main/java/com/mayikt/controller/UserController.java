package com.mayikt.controller;

import com.mayikt.entity.UserEntity;
import com.mayikt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-13 23:07
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/myIndex")
    public String myIndex(){
        return "myIndex";
    }

    /**
     * mybatis查询
     */
    @RequestMapping("/mybatisFindById")
    public UserEntity mybatisFindById(Integer id) {
        return userMapper.selectByUserId(id);
    }

    /**
     * mybatis插入
     */
    @RequestMapping("/mybatisInsertUser")
    public String mybatisInsertUser(String userName, Integer age) {
        int insert = userMapper.insertUser(userName, age);
        return insert > 0 ? "success" : "fail";
    }
}
