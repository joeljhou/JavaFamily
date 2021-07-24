package com.mayikt.service;

import com.mayikt.mapper.OrderInfoMapper;
import com.mayikt.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author 周宇
 * @create 2021-07-24 20:08
 */
@Service
public class OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private TransactionUtils transactionUtils;

    //1.声明式事务
    //@Transactional
    //public int addOrderInfo(int j) {
    //    TransactionStatus begin = null;
    //    try {
    //        //2.编程式事务
    //        begin = transactionUtils.begin();
    //        int i = orderInfoMapper.addOrderInfo();
    //        //j 传入0 报错
    //        int result = 1 / j;
    //        transactionUtils.commit(begin);
    //    } catch (Exception e) {
    //        transactionUtils.rollback(begin);
    //    }
    //    return 1;
    //}

    //3.Aop实现 编程式事务
    public int addOrderInfo(int j) {
        int i = orderInfoMapper.addOrderInfo();
        int result = 1 / j;
        return i;
    }

    //4.手动回滚
    public int addOrderInfo02(int j) {
        try {
            int i = orderInfoMapper.addOrderInfo();
            int result = 1 / j;
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
}
