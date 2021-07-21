package com.mayikt.api.service.impl;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.api.service.IMemberService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.ResponseBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-20 22:46
 */
@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    @RequestMapping("/getMember")
    public UserEntity getMember(@RequestParam("name") String name) {
        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name + "serverPort:" + serverPort);
        userEntity.setAge(20);
        return userEntity;
    }

    @Override
    @RequestMapping("/getUserInfo")
    public ResponseBase getUserInfo() {
        try {
            //会员服务接口产生1.5s的延迟
            Thread.sleep(1500);
        } catch (InterruptedException e) {

        }
        return setResultSuccess("订单服务调用会员服务..");
    }
}
