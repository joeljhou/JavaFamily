package com.mayikt.api.service.feign.fallback;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.api.service.IMemberService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.ResponseBase;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 周宇
 * @create 2021-07-21 23:49
 */
@Component
public class MemberServiceFallback extends BaseApiService implements IMemberService {

    @Override
    public UserEntity getMember(@RequestParam("name") String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("系统错误.没有获取到用户信息");
        return userEntity;
    }

    @Override
    public ResponseBase getUserInfo() {
        //服务降级处理
        return setResultError("系统错误，请稍后重试！");
    }
}
