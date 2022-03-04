package com.xhgj.advtypes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @create 2022-02-28 0:15
 */
@SpringBootTest
public class TestRedisGEO {

    @Autowired
    private RedisGEO redisGEO;

    @Test
    public void testAddLocation() {
        System.out.println(redisGEO.addLocation("cities", 116.28, 39.55, "beijing"));
    }

    @Test
    public void testAddLocations() {
        Map<String, GeoCoordinate> memberCoordinateMap = new HashMap<>();
        memberCoordinateMap.put("tianjin", new GeoCoordinate(117.12, 39.08));
        memberCoordinateMap.put("shijiazhuang", new GeoCoordinate(114.29, 38.02));
        memberCoordinateMap.put("tangshan", new GeoCoordinate(118.01, 39.38));
        memberCoordinateMap.put("baoding", new GeoCoordinate(115.29, 38.51));
        System.out.println(redisGEO.addLocation("cities", memberCoordinateMap));
    }

    @Test
    public void testNearby() {
        List<GeoRadiusResponse> responses = redisGEO.nearbyMore("cities", "beijing", 150, true, true);
        for (GeoRadiusResponse city : responses) {
            System.out.println(city.getMemberByString());  //按字符串获取成员
            System.out.println(city.getDistance()+" km");        //获取距离
            System.out.println(city.getCoordinate());      //获取坐标
            System.out.println("-------------------------");
        }
    }

}
