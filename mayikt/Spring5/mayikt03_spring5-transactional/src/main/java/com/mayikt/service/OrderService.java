package com.mayikt.service;

import com.mayikt.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 周宇
 * @create 2021-07-24 20:08
 */
@Service
public class OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    //声明式事务
    @Transactional
    public int addOrderInfo(int j) {
        int i = orderInfoMapper.addOrderInfo();
        //j 传入0 报错
        int result = 1 / j;
        return i;
    }
}
