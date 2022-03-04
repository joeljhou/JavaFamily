package com.mayikt.service.order.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 周宇
 * @create 2021-08-09 2:52
 */
@FeignClient("mayikt-member")
public interface MemberServiceFeign {

    /**
     * 提供发布的接口
     * @param userId
     * @return
     */
    @GetMapping("/getUser")
    String getUser(@RequestParam("userId") Integer userId);

}
