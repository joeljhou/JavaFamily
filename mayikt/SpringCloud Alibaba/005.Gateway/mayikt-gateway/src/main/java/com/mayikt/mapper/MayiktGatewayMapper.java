package com.mayikt.mapper;

import com.mayikt.entity.GateWayEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 周宇
 * @create 2021-08-23 0:55
 */
@Component
public interface MayiktGatewayMapper {

    @Select("select * from mayikt_gateway")
    List<GateWayEntity> gateWayAll();

    @Update("update mayikt_gateway set route_url = #{routeUrl} where route_id=#{routeId};")
    Integer updateGateWay(@Param("routeId") String routeId, @Param("routeUrl") String routeUrl);

}
