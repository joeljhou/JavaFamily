package com.mayikt.strategy.context;

import com.mayikt.strategy.PayStrategy;
import com.mayikt.strategy.factory.StrategyFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author 周宇
 * @create 2021-07-29 0:40
 */
@Component
public class PayContextStrategy {

    /**
     * 获取具体的策略实现
     */
    public String toPayHtml(String payCode) {
        if (StringUtils.isEmpty(payCode)) {
            return "payCode不能为空";
        }
        //1.使用策略工厂获取具体的策略实现
        PayStrategy payStrategy = StrategyFactory.getPayStrategy(payCode);
        if (payStrategy == null) {
            return "没有找到具体策略的实现..";
        }
        return payStrategy.toPayHtml();

    }

}
