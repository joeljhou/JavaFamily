package com.mayikt.mapper;

import org.apache.ibatis.annotations.Insert;

/**
 * @author 周宇
 * @create 2021-07-24 20:11
 */
public interface OrderInfoMapper {

    @Insert("INSERT INTO `order_info` (`orderName`, `orderDes`, `orderNumber`) VALUES ('mayikt', 'zhangsan', '111')")
    int addOrderInfo();

}
