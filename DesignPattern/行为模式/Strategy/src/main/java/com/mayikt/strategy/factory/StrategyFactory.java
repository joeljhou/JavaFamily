package com.mayikt.strategy.factory;

import com.mayikt.strategy.PayStrategy;
import com.mayikt.strategy.enumeration.PayEnumStrategy;

/**
 * @author 周宇
 * @create 2021-07-29 0:48
 * 使用策略工厂获取具体的策略实现+枚举+反射
 */
public class StrategyFactory {

    public static PayStrategy getPayStrategy(String strategyType) {
        try {
            //1.获取枚举中className
            String className = PayEnumStrategy.valueOf(strategyType).getClassName();
            //2.使用java反射技术初始化类
            return (PayStrategy) Class.forName(className).newInstance();
        } catch (Exception e) {
            return null;
        }
    }


}
