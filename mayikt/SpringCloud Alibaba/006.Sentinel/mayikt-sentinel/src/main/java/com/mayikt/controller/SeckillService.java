package com.mayikt.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author 周宇
 * @create 2021-09-09 3:27
 * 秒杀-热点参数限流
 */
@Slf4j
@RestController
public class SeckillService {

    public SeckillService() {
        initSeckillRule();
    }

    /**
     * 秒杀路由资源
     */
    private static final String SEKILL_RULE = "seckill";

    /**
     * 秒杀抢购
     */
    @RequestMapping("/seckill")
    public String seckill(Long userId, Long orderId) {
        try {
            Entry entry = SphU.entry(SEKILL_RULE, EntryType.IN, 1, userId);
            return "秒杀成功";
        } catch (Exception e) {
            return "当前用户访问过度频繁，请稍后重试!";
        }
    }

    /**
     * 使用注解的方式实现热点参数限流秒杀
     * 通过全局异常捕获对异常进程处理
     */
    @RequestMapping("/seckill1")
    @SentinelResource(value = SEKILL_RULE, fallback = "seckillFallback", blockHandler = "seckillBlockHandler")
    public String seckill1(Long userId, Long orderId) {
        return "秒杀成功1";
    }

    // seckill?userId=123456&orderId=644064779
    // seckill?userId=123456&orderId=644064779

    private void initSeckillRule() {
        ParamFlowRule rule = new ParamFlowRule(SEKILL_RULE)
                // 对我们秒杀接口第0个参数实现限流
                .setParamIdx(0)
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                // 每秒QPS最多只有1s
                .setCount(1);
        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
        log.info(">>>秒杀接口限流策略配置成功<<<");
    }

}
