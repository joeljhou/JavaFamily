package com.mayikt.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 周宇
 * @create 2021-07-15 9:44
 * 全局异常类
 */
@ControllerAdvice
//@ControllerAdvice(basePackages = "com.lqg.controller")  可以指定扫描范围
//@ControllerAdvice 是 controller 的一个辅助类，最常用的就是作为全局异常处理的切面类
public class GloblExceptionHandler {

    //@ResponseBody 返回json格式
    //modelAndView 返回页面
    @ExceptionHandler(RuntimeException.class)  //表示拦截运行时异常
    @ResponseBody
    public Map<Object,Object> errorResult(){
        Map<Object,Object> errorResultMap = new HashMap<>();
        errorResultMap.put("errorCode","500");
        errorResultMap.put("errorMsg","全局捕获异常系统错误~");
        return errorResultMap;
    }

}
