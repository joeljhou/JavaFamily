package com.mayikt.loadbalancer.impl;

import com.mayikt.loadbalancer.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 周宇
 * @create 2021-08-09 0:21
 * 轮询实现
 */
@Component
public class RotationLoadBalancer implements LoadBalancer {

    /**
     * 访问次数 从0开始计数
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances) {
        int index = atomicInteger.incrementAndGet() % serviceInstances.size();
        ServiceInstance serviceInstance = serviceInstances.get(index);
        return serviceInstance;
    }

}
