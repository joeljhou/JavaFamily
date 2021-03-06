package com.mayikt.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2021-09-08 2:13
 */
@Slf4j
@RestController
public class UserController {


    @Value("${server.port}")
    private String serverPort;


    /*==========================流控规则==========================*/
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

    /**
     * SentinelApplicationRunner 启动运行类中加载getUser限流规则
     */
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
     * 加载nacos配置的流控规则
     * mayikt-order-sentinel json格式
     * [
     *     {
     *         "resource": "getOrderSentinel",
     *         "limitApp": "default",
     *         "grade": 1,
     *         "count": 2,
     *         "strategy": 0,
     *         "controlBehavior": 0,
     *         "clusterMode": false
     *     }
     * ]
     */
    @SentinelResource(value = "getOrderSentinel", blockHandler = "getOrderQpsException")
    @RequestMapping("/getOrderSentinel")
    public String getOrderSentinel() {
        return "getOrderSentinel";
    }

    /**
     * 流控规则-基于QPS数量处理限流（阈值类型：QPS）
     * 每秒最多只会有一个请求处理该业务逻辑，超出该阈值的情况下，直接拒绝访问
     * @SentinelResource  value参数：流量规则资源名称
     * blockHandler 限流/熔断出现异常执行的方法
     * Fallback 服务的降级执行的方法
     */
    @SentinelResource(value = "getOrderDashboard", blockHandler = "getOrderQpsException")
    @RequestMapping("/getOrderDashboard")
    public String getOrderDashboard() {
        return "getOrderDashboard";
    }

    /**
     * 流控规则-基于并发数量处理限流（阈值类型：并发线程数）
     * 每次最多只会有一个线程处理该业务逻辑，超出该阈值的情况下，直接拒绝访问
     */
    @SentinelResource(value = "getOrderThrad", blockHandler = "getOrderQpsException")
    @RequestMapping("/getOrderThrad")
    public String getOrderThrad(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        return "getOrderThrad";
    }

    /**
     * 被限流后返回的提示
     */
    public String getOrderQpsException(BlockException e) {
        e.printStackTrace();
        return "该接口已经被限流啦!";
    }



    /*==========================熔断规则==========================*/
    /**
     * 熔断规则-基于RT模式实现熔断降级 RT模式：平均响应时间
     */
    @SentinelResource(value = "getOrderDowngradeRtType",fallback = "getOrderDowngradeRtTypeFallback")
    @RequestMapping("/getOrderDowngradeRtType")
    public String getOrderDowngradeRtType(){
        try{
            Thread.sleep(300);
        }catch (Exception e){
        }
        return "getOrderDowngradeRtType";
    }

    public String getOrderDowngradeRtTypeFallback(){
        return "服务降级啦，当前服务器请求次数过多，请稍后再试";
    }


    /**
     * 熔断规则-基于错误率实现熔断降级 异常比例模式
     */
    @SentinelResource(value = "getOrderDowngradeErrorType", fallback = "getOrderDowngradeErrorTypeFallback")
    @RequestMapping("/getOrderDowngradeErrorType")
    public String getOrderDowngradeErrorType(int age) {
        int j = 1 / age;
        return "正常执行我们的业务逻辑";
    }

    public String getOrderDowngradeErrorTypeFallback(int age) {
        return "错误率太高，暂时无法访问该接口，请稍后重试!";
    }

}
