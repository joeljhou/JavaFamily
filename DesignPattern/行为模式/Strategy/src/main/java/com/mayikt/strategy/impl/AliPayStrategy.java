package com.mayikt.strategy.impl;

import com.mayikt.strategy.PayStrategy;

/**
 * @author 周宇
 * @create 2021-07-29 0:36
 */
public class AliPayStrategy implements PayStrategy {
    @Override
    public String toPayHtml() {
        return "支付宝支付实现..";
    }
}
