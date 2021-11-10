package com.mayikt.api.service;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 周宇
 * @create 2021-07-20 22:15
 * 会员服务接口
 */
public interface IMemberService {

    //实体类存放在接口项目，还是存放在实现项目 实体类存放在接口项目里面
    //实体类和定义的接口信息存放在接口项目
    //代码实现存放在接口实现类里面
    @RequestMapping("/getMember")
    UserEntity getMember(@RequestParam("name") String name);

    @RequestMapping("/getUserInfo")
    ResponseBase getUserInfo();

}
