package com.mayikt.service;

import com.mayikt.entity.GateWayEntity;
import com.mayikt.mapper.MayiktGatewayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @create 2021-08-23 0:54
 */
@Component
public class GatewayService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    @Autowired
    private MayiktGatewayMapper mayiktGatewayMapper;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void initAllRoute() {
        // 从数据库查询配置的网关配置
        List<GateWayEntity> gateWayEntities = mayiktGatewayMapper.gateWayAll();
        for (GateWayEntity gw : gateWayEntities) {
            loadRoute(gw);
        }
    }

    /**
     * 加载路由策略
     */
    public String loadRoute(GateWayEntity gateWayEntity) {
        RouteDefinition definition = new RouteDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        PredicateDefinition predicate = new PredicateDefinition();
        FilterDefinition filterDefinition = new FilterDefinition();
        Map<String, String> filterParams = new HashMap<>(8);
            // 如果配置路由type为0的话 则从注册中心获取服务
        URI uri = null;
        if (gateWayEntity.getRouteType().equals("0")) {
            uri = UriComponentsBuilder.fromUriString("lb://" + gateWayEntity.getRouteUrl() + "/").build().toUri(); //"lb://mayikt-member"
        } else {
            uri = UriComponentsBuilder.fromHttpUrl(gateWayEntity.getRouteUrl()).build().toUri();
        }
        // 定义的路由唯一的id
        definition.setId(gateWayEntity.getRouteId());  //"member"
        predicate.setName("Path");
        //路由转发地址
        predicateParams.put("pattern",  gateWayEntity.getRoutePattern()); // "/member/**"
        predicate.setArgs(predicateParams);

        // 名称是固定的, 路径去前缀
        filterDefinition.setName("StripPrefix");
        filterParams.put("_genkey_0", "1");
        filterDefinition.setArgs(filterParams);
        definition.setPredicates(Arrays.asList(predicate));
        definition.setFilters(Arrays.asList(filterDefinition));
        definition.setUri(uri);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }
}
