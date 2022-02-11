package com.mayikt;

import com.mayikt.cglib.proxy.CglibMethodInterceptor;
import com.mayikt.jdk.proxy.JdkInvocationHandler;
import com.mayikt.proxy.OrderServiceProxy_extendsClass;
import com.mayikt.proxy.OrderServiceProxy_impIinterface;
import com.mayikt.service.MemberService;
import com.mayikt.service.OrderService;
import com.mayikt.service.impl.MemberServiceImpl;
import com.mayikt.service.impl.OrderServiceImpl;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 周宇
 * @create 2021-07-27 22:18
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        //1.基于接口实现方式
        OrderServiceProxy_impIinterface orderServiceProxyImpl = new OrderServiceProxy_impIinterface(new OrderServiceImpl());
        orderServiceProxyImpl.order();
        System.out.println("==================== 1.基于接口实现方式");

        //2.基于继承的实现方式
        OrderServiceProxy_extendsClass orderServiceProxyExtends = new OrderServiceProxy_extendsClass();
        orderServiceProxyExtends.order();
        System.out.println("==================== 2.基于继承的实现方式");

        //3.使用JDL动态代理
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");  新版本
        JdkInvocationHandler jdkInvocationHandler = new JdkInvocationHandler(new OrderServiceImpl());
        OrderService orderService1 = jdkInvocationHandler.getProxy();
        orderService1.order();
        System.out.println("==================== 3.使用JDL动态代理");

        //4.使用CGLIB动态代理 代理接口
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        CglibMethodInterceptor cglibMethodInterceptor = new CglibMethodInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MemberServiceImpl.class);  //设置被代理类的父类
        enhancer.setCallback(cglibMethodInterceptor);     //设置回调对象
        MemberServiceImpl orderServiceImpl = (MemberServiceImpl) enhancer.create();  //创建代理对象
        orderServiceImpl.getMember();
        System.out.println("==================== 4.使用CGLIB动态代理 代理接口");

        //5.使用CGLIB动态代理 代理类
        final MemberService target = new MemberServiceImpl();  //创建被代理类的对象
        Enhancer enhancer1 = new Enhancer();
        enhancer1.setSuperclass(MemberService.class);  //设置代理类
        enhancer1.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            //代理逻辑
            if (method.getName().equals("getMember")) {
                System.out.println("<<<<<cglib before...>>>>>>>");
                Object invoke = method.invoke(target, objects);
                System.out.println("<<<<<cglib after...>>>>>>>");
                return invoke;
            }
            return null;
        });
        MemberServiceImpl orderServiceImpl2 = (MemberServiceImpl) enhancer.create();
        orderServiceImpl2.getMember();
        System.out.println("==================== 5.使用CGLIB动态代理 代理类");
    }
}
