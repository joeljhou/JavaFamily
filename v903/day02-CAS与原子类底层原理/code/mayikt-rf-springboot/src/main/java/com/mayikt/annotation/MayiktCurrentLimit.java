package com.mayikt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouyu
 * @create 2021-05-27 4:43
 * 自定义一个限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MayiktCurrentLimit {

    /**
     * 限流的名称
     * @return
     */
    String name() default "";

    /**
     * QPS：每s能够访问的次数20
     * 底层：令牌桶
     * @return
     */
    double token() default 20;

}
