package com.mayikt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-06-02 17:16
 */
@Aspect
@Component
public class ExtMayiktThreadAop {

    @Around(value = "@annotation(com.mayikt.annotation.MayiktAsync)")
    public void around(ProceedingJoinPoint joinPoint){
        try {
            // 正常需要结合到线程池
            new Thread(() -> {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            },"mayikt线程").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
