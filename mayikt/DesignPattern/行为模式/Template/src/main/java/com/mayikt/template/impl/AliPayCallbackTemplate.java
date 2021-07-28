package com.mayikt.template.impl;

import com.mayikt.template.AbstractPayCallbackTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 周宇
 * @create 2021-07-29 1:54
 */
@Slf4j
@Component
public class AliPayCallbackTemplate extends AbstractPayCallbackTemplate {
    @Override
    protected Map<String, String> verifySignature() {
        //>>>假设一下为阿里pay回调报文开始>>>>>>>>>>>>>>>>>
        log.info(">>>第一步 解析支付宝报文 ...verifySignature()");
        Map<String,String> verifySignature  = new HashMap<>();
        verifySignature.put("price","1399");
        verifySignature.put("orderDes","充值蚂蚁课堂会员");
        //支付状态为 1 表示成功..
        verifySignature.put("aliPayMentStatus","1");
        verifySignature.put("aliPayOrderNumber","20210729011");
        //>>>假设一下为阿里pay回调报文结束>>>>>>>>>>>>>>>>>

        //解析保温是否成功 或者签名成功返回200 为成功
        verifySignature.put("analysisCode","200");
        return verifySignature;
    }

    @Override
    protected String asyncService(Map<String, String> verifySignature) {
        log.info(">>>第三步 支付宝支付 asyncService() verifySignatureMap:{}",verifySignature);
        String payMentStatus = verifySignature.get("aliPayMentStatus");
        if (payMentStatus.equals("1")){
            String aliPayOrderNumber = verifySignature.get("aliPayOrderNumber");
            log.info(">>>orderNumber:{aliPayOrderNumber},已经支付成功，修改订单状态已支付");
        }
        return resultSuccess();
    }

    @Override
    protected String resultSuccess() {
        return "success";
    }

    @Override
    protected String resultFail() {
        return "fail";
    }

}
