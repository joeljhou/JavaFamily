package com.mayikt.demo.api.service.impl;

import com.mayikt.demo.api.service.DemoApiService;

/**
 * @author 周宇
 * @create 2021-09-10 23:36
 */
public class DemoApiServiceImpl implements DemoApiService {
    @Override
    public String getUser(Long userId) {
        System.out.println("生产者调用消费者服务接口userId:" + userId);
        return "zhouyu666";
    }
}
