package com.mayikt.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2021-09-08 2:13
 */
@RestController
public class UserController {


    @Value("${server.port}")
    private String serverPort;

    private static final String SENTINEL_KEY = "getUser";

    /**
     * 初始化路由策略
     */
    @RequestMapping("/initFlowQpsRule")
    public String initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource(SENTINEL_KEY);
        // QPS控制在1以内
        rule1.setCount(1);
        // QPS限流
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
        return "初始化配置策略成功..";
    }

    @RequestMapping("/getUser")
    public String getUser() {
        Entry entry = null;
        try {
            entry = SphU.entry(SENTINEL_KEY);
        } catch (Exception e) {
            return "当前频率访问次数过多，请稍后重试!";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return "mayikt：" + serverPort;
    }


    /**
     * 注解形式配置管理Api限流
     * @SentinelResource  value参数：流量规则资源名称
     * blockHandler 限流/熔断出现异常执行的方法
     * Fallback 服务的降级执行的方法
     */
    @SentinelResource(value = "getAnnotationConsole", blockHandler = "getOrderQpsException")
    @RequestMapping("/getAnnotationConsole")
    public String getAnnotationConsole() {
        return "getAnnotationConsole接口";
    }

    /**
     * 被限流后返回的提示
     */
    public String getOrderQpsException(BlockException e) {
        e.printStackTrace();
        return "该接口已经被限流啦!";
    }

}
