package com.mayikt.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author 周宇
 * @create 2021-08-09 0:20
 */
public interface LoadBalancer {

    /**
     * 根据多个不同的地址 返回单个调用rpc地址
     * @param serviceInstances
     * @return
     */
    ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances);


}
