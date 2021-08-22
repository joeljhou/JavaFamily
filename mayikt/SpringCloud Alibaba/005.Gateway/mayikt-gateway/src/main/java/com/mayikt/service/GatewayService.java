package com.mayikt.service;

import com.mayikt.mapper.MayiktGatewayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-08-23 0:54
 */
@Component
public class GatewayService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    //@Autowired
    //private MayiktGatewayMapper mayiktGateway;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }
}
