package com.mayiky.mapper;

import com.mayiky.mapper.entity.GatewayHandlerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


/**
 * @author 周宇
 * @create 2021-07-29 23:05
 */
@Mapper
public interface GatewayHandlerMapper {

    /**
     * 获取第一个GatewayHandler
     *
     * @return
     */
    @Select("select handler_name as handlerName,handler_id as handlerId,prev_handler_id as prevhandlerId,next_handler_id as nexthandlerid from gateway_handler where prev_handler_id is null;")
    public GatewayHandlerEntity getFirstGatewayHandler();

    @Select("select handler_name as handlerName,handler_id as handlerId,prev_handler_id as prevhandlerId,next_handler_id as nexthandlerid from gateway_handler where handler_id = #{handlerId};")
    public GatewayHandlerEntity getByHandler(String handlerId);


}
