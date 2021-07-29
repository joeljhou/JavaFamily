package com.mayiky.service;

import com.mayiky.handler.GatewayHandler;
import com.mayiky.mapper.GatewayHandlerMapper;
import com.mayiky.mapper.entity.GatewayHandlerEntity;
import com.mayiky.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author 周宇
 * @create 2021-07-29 23:22
 */
@Component
public class HandlerService {

    @Autowired
    private GatewayHandlerMapper gatewayHandlerMapper;

    private GatewayHandler firstGatewayHandler;
    /**
     * 从数据中获取中
     * @return
     */
    public GatewayHandler getDbFirstGatewayHandler(){
        if (firstGatewayHandler!=null){
            return firstGatewayHandler;
        }
        //1.从数据库中查询第一个GatewayHandler
        GatewayHandlerEntity firstGatewayHandlerEntiy = gatewayHandlerMapper.getFirstGatewayHandler();
        //2.从springboot容器中获取第一个handler对象
        String firstGatewayHandlerId = firstGatewayHandlerEntiy.getHandlerId();
        if (StringUtils.isEmpty(firstGatewayHandlerId)){
            return null;
        }
        GatewayHandler firstGatewayHandler = SpringUtils.getBean(firstGatewayHandlerId, GatewayHandler.class);
        if (firstGatewayHandler==null){
            return null;
        }
        //3.使用while循环递归实现下一个节点关联
        String nextBeanHandlerId = firstGatewayHandlerEntiy.getNextHandlerId();
        GatewayHandler tempNextGatewayHandler = firstGatewayHandler;
        while (!StringUtils.isEmpty(nextBeanHandlerId)){
            //4.从SpringBoot容器中获取下一个handler对象
            GatewayHandler nextGatewayHandler = SpringUtils.getBean(nextBeanHandlerId, GatewayHandler.class);
            if (nextGatewayHandler==null){
                break;
            }
            //5.通过数据库查询到下一个handler信息
            GatewayHandlerEntity nextGatewayHandlerEntity = gatewayHandlerMapper.getByHandler(nextBeanHandlerId);
            if (nextGatewayHandlerEntity==null){
                break;
            }
            nextBeanHandlerId = nextGatewayHandlerEntity.getNextHandlerId();

            //6.设置关联下一个handler对象
            tempNextGatewayHandler.setNextGatewayHandler(nextGatewayHandler);
            tempNextGatewayHandler = nextGatewayHandler;
        }
        this.firstGatewayHandler = firstGatewayHandler;
        return firstGatewayHandler;
    }

}
