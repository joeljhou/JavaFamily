package com.xhgj.advtypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @create 2022-02-27 23:59
 */
@Component
public class RedisGEO {

    public final static String RS_GEO_NS = "rg:";

    @Autowired
    private JedisPool jedisPool;


    /**
     * 添加位置
     *
     * @param key      键
     * @param logitude 经度
     * @param latiude  维度
     * @param member   成员名
     * @return
     */
    public Long addLocation(String key, double logitude, double latiude, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(RS_GEO_NS + key, logitude, latiude, member);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 批量添加位置
     *
     * @param key                 键
     * @param memberCoordinateMap 地理坐标
     * @return
     */
    public Long addLocation(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(RS_GEO_NS + key, memberCoordinateMap);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 查询成员半径1km内的坐标
     *
     * @param key      键
     * @param member   成员名
     * @param radius   半径
     * @param withDist 是否显示距离
     * @param isASC    是否升序排列
     * @return
     */
    public List<GeoRadiusResponse> nearbyMore(String key, String member, double radius, boolean withDist, boolean isASC) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            GeoRadiusParam geoRadiusParam = new GeoRadiusParam();
            if (withDist) geoRadiusParam.withDist();   //是否显示距离
            if (isASC) geoRadiusParam.sortAscending(); //是否升序排列
            else geoRadiusParam.sortDescending();
            return jedis.georadiusByMember(RS_GEO_NS + key, member, radius, GeoUnit.KM, geoRadiusParam);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
