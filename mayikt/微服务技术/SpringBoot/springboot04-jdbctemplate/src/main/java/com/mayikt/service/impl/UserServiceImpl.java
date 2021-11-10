package com.mayikt.service.impl;

import com.mayikt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 周宇
 * @create 2021-07-13 21:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean inserUser(String name, Integer age) {
        int update = jdbcTemplate.update("insert into users values(null,?,?);", name, age);
        return update > 0 ? true : false;
    }
}
