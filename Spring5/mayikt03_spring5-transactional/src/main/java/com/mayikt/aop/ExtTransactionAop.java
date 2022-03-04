package com.mayikt.aop;

import com.mayikt.utils.TransactionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

/**
 * @author 周宇
 * @create 2021-07-24 22:29
 */
@Component
@Slf4j
@Aspect
public class ExtTransactionAop {

    @Autowired
    private TransactionUtils transactionUtils;

    @Around(value = "@annotation(com.mayikt.aop.MeiteTransactional)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        TransactionStatus begin = null;
        try {
            begin = transactionUtils.begin();
            joinPoint.proceed();
            transactionUtils.commit(begin);
        } catch (Throwable throwable) {
            if (begin != null) {
                transactionUtils.rollback(begin);
            }
        }
    }

}
