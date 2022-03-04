package com.mayikt.aop;

import java.lang.annotation.*;

/**
 * @author 周宇
 * @create 2021-07-24 22:30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MeiteTransactional {

}
