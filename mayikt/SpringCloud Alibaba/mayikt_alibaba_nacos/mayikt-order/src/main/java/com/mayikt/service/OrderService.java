package com.mayikt.service;

import com.mayikt.loadbalancer.LoadBalancer;
import com.mayikt.loadbalancer.impl.RotationLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 周宇
 * @create 2021-08-08 23:54
 */
@RestController
public class OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    /**
     * 订单服务调用会员服务
     *
     * @return
     */
    @RequestMapping("/orderToMember")
    public Object orderToMember() {
        //1.根据服务名称 从 注册中心获取集群列表地址
        List<ServiceInstance> instances = discoveryClient.getInstances("mayikt-member");
        //2.列表任选一个 实现本地rpc调用 rest
        //ServiceInstance serviceInstance = instances.get(0);
        //轮询 采用我们的负载均衡算法
        ServiceInstance serviceInstance = loadBalancer.getSingleAddres(instances);
        String result = restTemplate.getForObject(serviceInstance.getUri()+"/getUser", String.class);
        return "订单调用会员返回结果：" + result;
    }

}
