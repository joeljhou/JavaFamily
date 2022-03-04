package com.mayikt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2021-07-17 22:46
 */
@RestController
public class ZKOrderController {

    //restTemplate 是由SpringBoot Web组件提供 默认整合ribbon负载均衡器
    //rest方式底层采用httpclient技术
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 在Spring中有两种方式调用rest,feign(SpringCloud)
     */
    @RequestMapping("/getOrderToMember")
    public String getOrderToMember() {
        //有两种方式，一种是采用服务别名方式调用，另一种直接调用 使用别名去注册中心上获取对应的服务调用地址
        String memberUrl = "http://zk-member/getMember";
        String result = restTemplate.getForObject(memberUrl, String.class);
        System.out.println("会员服务调用订单服务，result：" + result);
        return result;
    }

    /**
     * 获取注册中心上服务列表信息
     */
    @RequestMapping("/discoveryClientMember")
    public List<String> discoveryClientMember() {
        List<ServiceInstance> list = discoveryClient.getInstances("zk-member");
        List<String> services = new ArrayList<>();
        for (ServiceInstance serviceInstance : list) {
            if (serviceInstance != null) {
                services.add(serviceInstance.getUri().toString());
            }
        }
        return services;
    }

}
