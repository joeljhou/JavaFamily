package com.mayikt.strategy.enumeration;

/**
 * @author 周宇
 * @create 2021-07-29 0:43
 * 策略枚举类 存放所有的策略的实现
 */
public enum PayEnumStrategy {
    /**
     * 支付宝支付
     */
    ALI_PAY("com.mayikt.strategy.impl.AliPayStrategy"),
    /**
     * 银联支付
     */
    WECHAT_PAY("com.mayikt.strategy.impl.YinlianPayStrateay"),
    /**
     * 小米支付
     */
    XIAOMI_PAY("com.mayikt.strategy.impl.XiaoPayStrateay");

    PayEnumStrategy(String className) {
        this.className = className;
    }

    /**
     * class完整地址
     */
    private String className;
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

}
