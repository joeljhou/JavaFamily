package com.mayikt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-14 22:38
 */
@Component
@Slf4j
public class MemberServiceAsync {

    /**
     * @Async注解上整合线程池名称
     */
    @Async("taskExecutor")
    public String smsAsync() {
        log.info(">02<");
        try {
            log.info(">正在发送短信..<");
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        log.info(">03<");
        return "短信发送完成!";
    }

}
