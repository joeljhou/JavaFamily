package com.mayikt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author 周宇
 * @create 2021-07-24 21:33
 */
@Component
public class TransactionUtils {

    /**
     * 获取到当前的事务管理器
     */
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    public TransactionStatus begin(){
        System.out.println("获取当前事务 >>>>>");
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    /**
     * 提交事务
     */
    public void commit(TransactionStatus transaction){
        System.out.println("提交当前事务 >>>>>");
        dataSourceTransactionManager.commit(transaction);
    }

    /**
     * 回滚事务
     */
    public void rollback(TransactionStatus transaction){
        System.out.println("回滚当前事务 >>>>>");
        dataSourceTransactionManager.rollback(transaction);
    }

}
