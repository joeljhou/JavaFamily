package com.mayikt.api.service.impl;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.api.service.IMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-20 22:46
 */
@RestController
public class MemberServiceImpl implements IMemberService {

    @Override
    @RequestMapping("/getMember")
    public UserEntity getMember(@RequestParam("name") String name) {
        UserEntity userEntity = new UserEntity().setName(name).setAge(18);
        return userEntity;
    }
}
