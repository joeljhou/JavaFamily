package com.mayikt.entity;

/**
 * @author 周宇
 * @create 2021-07-23 23:12
 */
public class MemberEntity {
    private String name;

    public MemberEntity() {
        System.out.println("[第一步]-无参构造函数被执行---反射机制调用");
    }

    public void setName(String name) {
        System.out.println("[第二步]-set方法初始化属性---反射机制调用");
        this.name = name;
    }

    /**
     * 回调调用init初始化方法
     */
    public void initMethod() {
        System.out.println("[第三步]-回调调用init初始化方法");
    }

    /**
     * destroyMethod
     */
    public void destroyMethod() {
        System.out.println("[第五步]-回调调用destroyMethod方法");
    }

}
