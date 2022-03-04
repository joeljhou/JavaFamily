package com.mayikt.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 周宇
 * @create 2021-07-20 12:26
 * 纯手写Ribbon本地负载均衡效果
 */
@RestController
public class ExtRibbonController {

    //可以获取注册中心上服务列表
    @Autowired
    private DiscoveryClient discoveryClient;

    private static RestTemplate restTemplate = new RestTemplateBuilder().build();
    /*==========存手写实现本地负载均衡==========*/

    //接口的请求总数
    //使用原子计数器 因为线程安全 效率非常高 使用cas 无锁机制
    private int reqtCount = 1;

    @RequestMapping("/ribbonMember")
    public String ribbonMember() {
        //1.获取对应服务器远程调用地址
        String serviceUrl = getServiceUrl() + "/getMember";
        if (StringUtils.isEmpty(serviceUrl)) {
            return "请求地址为null";
        }
        //2.可以直接使用httpclient技术实现远程调用
        String result = restTemplate.getForObject(serviceUrl, String.class);
        System.out.println("会员服务调用订单服务，result：" + result);
        return result;
    }

    /*实现本地负载均衡算法*/
    public String getServiceUrl() {
        List<ServiceInstance> instances = discoveryClient.getInstances("app-mayikt-member");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        //获取服务器集群个数
        int size = instances.size();
        int inex = reqtCount % size;
        reqtCount++;
        return instances.get(inex).getUri().toString();
    }
}
