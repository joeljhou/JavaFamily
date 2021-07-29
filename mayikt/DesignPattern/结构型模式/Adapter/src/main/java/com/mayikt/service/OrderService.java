package com.mayikt.service;

import java.util.Map;

/**
 * @author 周宇
 * @create 2021-07-30 0:42
 */
public class OrderService {

    /**
     * V1版本 提供了一个接口，入参是map类型
     * V2版本 能够支持list类型
     * @param map
     */
    public void forMap(Map map) {
        for (int i = 0; i < map.size(); i++) {
            String value = (String) map.get(i);
            System.out.println("value:" + value);
        }
    }

    //适配器模式 支持新版本list类型 中间使用适配器

}
