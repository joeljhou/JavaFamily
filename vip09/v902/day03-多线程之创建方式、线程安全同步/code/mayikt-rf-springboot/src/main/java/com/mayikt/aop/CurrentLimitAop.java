package com.mayikt.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.mayikt.annotation.MayiktCurrentLimit;
import java.util.concurrent.ConcurrentHashMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zhouyu
 * @create 2021-05-27 4:59
 */
@Aspect
@Component
public class CurrentLimitAop {

    /**
     * 每秒生成1.0个令牌
     */
    //private RateLimiter rateLimiter = RateLimiter.create(1.0);
    private ConcurrentHashMap<String, RateLimiter> rateLimiters = new ConcurrentHashMap();

    @Before(value = "@annotation(com.mayikt.annotation.MayiktCurrentLimit)")
    public void before(){
        System.out.println("---------------前置通知---------------");
    }

    @After(value = "@annotation(com.mayikt.annotation.MayiktCurrentLimit)")
    public void after(){
        System.out.println("---------------后置通知---------------");
    }

    @Around(value = "@annotation(com.mayikt.annotation.MayiktCurrentLimit)")
    public Object around(ProceedingJoinPoint joinPoint){
        try {
            //获取拦截的方法名
            Signature sig = joinPoint.getSignature();
            //获取拦截的方法名
            MethodSignature methodSignature = (MethodSignature) sig;
            // 判断方法上是否有加上该注解，如果有加上注解则限流
            MayiktCurrentLimit mayiktCurrentLimit = methodSignature.getMethod().getDeclaredAnnotation(MayiktCurrentLimit.class);
            //if(mayiktCurrentLimit == null){
            //    // 执行目标方法
            //    return joinPoint.proceed();
            //}
            String name = mayiktCurrentLimit.name();    // 获取注解上的name
            double token = mayiktCurrentLimit.token();  // 获取注解上的token
            RateLimiter rateLimiter = rateLimiters.get(name);
            if(rateLimiter==null){
                rateLimiter = RateLimiter.create(token);
                rateLimiters.put(name,rateLimiter);
            }
            // 开始限流
            boolean result = rateLimiter.tryAcquire();
            if (!result) {
                return  "当前访问人数过多，请稍后重试!";
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return "系统出现了错误!";
        }
    }

}
