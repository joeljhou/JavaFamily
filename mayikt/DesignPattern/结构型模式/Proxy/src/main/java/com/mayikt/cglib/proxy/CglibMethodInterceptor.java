package com.mayikt.cglib.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 周宇
 * @create 2021-07-28 0:02
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("<<<<<cglib 日志收集开始...>>>>>>>");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("<<<<<cglib 日志收集结束...>>>>>>>");
        return result;
    }
}
