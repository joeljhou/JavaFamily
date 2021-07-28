package com.mayikt.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 周宇
 * @create 2021-07-29 1:42
 * 抽象方法模板
 */
@Slf4j
public abstract class AbstractPayCallbackTemplate {

    /**
     * 定义共同行为的骨架
     *
     * @return
     */
    public String asyncCallBack() {
        //1.验证参数和验证前面
        Map<String, String> verifySignature = verifySignature();
        //2.日志收集 相同的
        payLog(verifySignature);
        //3.获取验证签名状态
        String analysisCode = verifySignature.get("analysisCode");
        if (!analysisCode.equals("200")){
            return resultFail();
        }
        //3.更改数据库状态 返回不同支付结果
        return asyncService(verifySignature);
    }


    @Async //使用多线程异步写入
    public void payLog(Map<String, String> verifySignature) {
        log.info("第二步 日志写入数据库中..verifySignature:{}," + verifySignature);
    }

    /**
     * 验证参数...
     * @return
     */
    protected abstract Map<String, String> verifySignature();

    //返回成功结果
    protected abstract String resultSuccess();
    //返回失败结果
    protected abstract String resultFail();

    /**
     * 执行修改订单状态和返回不同结果..
     * @param verifySignature
     * @return
     */
    protected abstract String asyncService(Map<String, String> verifySignature);
}
