package com.mayikt.service;

import com.mayikt.loadbalancer.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 订单服务调用会员服务
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


    /**
     * 基于Ribbon实现本地负载均衡
     * @return
     */
    @RequestMapping("/orderToRibbonMember")
    public Object orderToRibbonMember() {
        String result = restTemplate.getForObject("http://mayikt-member/getUser", String.class);
        return "订单调用会员返回结果：" + result;
    }

    /**
     * LoadBalancerClient负载均衡器
     * @return 底层默认原理是调用ribbon的实现客户端负载均衡器
     */
    @RequestMapping("/loadBalancerClient")
    public Object loadBalancerClient() {
        return loadBalancerClient.choose("mayikt-member");
    }

}
