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
     * 目标对象
     */
    private Object target;

    /**
     * 构造方法，给我们要代理的真实对象赋初值
     */
    public JdkInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 该方法负责集中处理动态代理类上的所有方法调用
     * 调用处理器根据这三个参数进行预处理或分派到委托类实例上反射执行
     *
     * @param proxy  代理类实例
     * @param method 目标方法
     * @param args   方法需要传递的参数
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("使用Jdk动态代理打印日志开始");
        Object result = method.invoke(target, args);
        System.out.println("使用Jdk动态代理打印日志结束");
        return result;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}

