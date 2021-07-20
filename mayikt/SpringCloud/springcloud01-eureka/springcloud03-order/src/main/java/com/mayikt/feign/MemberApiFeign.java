package com.mayikt.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 周宇
 * @create 2021-07-20 15:53
 */
@FeignClient(name = "app-mayikt-member")
public interface MemberApiFeign {

    @RequestMapping("/getMember")
    public String getMember();

}
