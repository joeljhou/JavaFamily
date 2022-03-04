package com.mayikt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-24 11:21
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAop {
    //@Aspect 定义切面类
    //@EnableAspectJAutoProxy 开启Aop

    /**
     * 定义切入点：Pointcut 开始方法拦截入口
     */
    @Pointcut("execution (* com.mayikt.service..*.*(..))")
    public void logAop() {

    }

    /**
     * 环绕通知
     */
    @Around(value = "logAop()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object rtValue = null;
        try {
            System.out.println("【环绕通知在方法之前执行】>>>");
            rtValue = joinPoint.proceed();// 执行目标方法
            System.out.println("【环绕通知在方法之后执行】>>>");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return rtValue;
    }

    /**
     * 前置通知
     */
    @Before("logAop()")
    public void doBefore(JoinPoint point) {
        System.out.println("【前置通知】>>>调用方法之前拦截");
    }

    /**
     * 后置通知
     */
    @After("logAop()")
    public void doAfter() {
        System.out.println("【后置通知】>>>调用方法之后拦截");
    }

    /**
     * 运行通知
     */
    @AfterReturning("logAop()")
    public void AfterReturning(JoinPoint joinPoint) {
        //程序报错的话，不会执行运行通知
        System.out.println("【运行通知】>>>");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("logAop()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("【异常通知】>>>");
    }


}
