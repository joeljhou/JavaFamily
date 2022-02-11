package com.mayikt.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 周宇
 * @create 2021-07-27 22:41
 * JDK动态代理 调用处理器实现类
 */
public class JdkInvocationHandler implements InvocationHandler {
    /**
     * 被代理的目标对象
     */
    private Object target;
    public JdkInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 处理代理实例，并返回结果
     * 该方法负责集中处理动态代理类上的所有方法调用
     * 调用处理器根据这三个参数进行预处理或分派到委托类实例上反射执行
     * @param proxy  代理类实例
     * @param method 目标方法
     * @param args   方法需要传递的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("使用Jdk动态代理打印日志开始");
        Object result = method.invoke(target, args);
        System.out.println("使用Jdk动态代理打印日志结束");
        return result;
    }

    /**
     * 生成得到代理类
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}

