package com.mayikt.aop;

import com.mayikt.utils.TransactionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

/**
 * @author 周宇
 * @create 2021-07-24 11:21
 */
@Aspect
@Component
@Scope("prototype")  //多例
public class TransactionAop {
    //TransactionAop Aop切面是原型的。

    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 定义切入点：Pointcut 开始方法拦截入口
     */
    @Pointcut("execution (* com.mayikt.service..*.*(..))")
    public void transactionAop() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "transactionAop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        TransactionStatus begin = null;
        try {
            begin = transactionUtils.begin();
            System.out.println("【环绕通知在方法之前执行】>>>");
            Object proceed = joinPoint.proceed();// 执行目标方法
            System.out.println("【环绕通知在方法之后执行】>>>");
            transactionUtils.commit(begin);
            return proceed;
        } catch (Throwable throwable) {
            //思考如何做回滚? 抛出异常
            transactionUtils.rollback(begin);
            return 0;
        }
    }

}
