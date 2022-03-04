package com.mayikt.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 周宇
 * @create 2021-09-09 1:54
 * 配置网关路由的流控规则
 */
@Slf4j
@Component
public class SentinelApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initGatewayRules();
    }

    /**
     * 配置路由策略中 mayikt 的限流规则
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("mayikt")
                // 限流阈值
                .setCount(1)
                // 统计时间窗口，单位是秒，默认是 1 秒
                .setIntervalSec(1)
        );
        GatewayRuleManager.loadRules(rules);
    }

}
