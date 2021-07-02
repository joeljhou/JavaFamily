package com.mayikt.service.manage;

import com.mayikt.annotation.MayiktAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhouyu
 * @create 2021-05-27 3:30
 */
@Component
@Slf4j
public class OrderManage {

    //@Async
    @MayiktAsync
    public void asyncLog(){
        try {
            Thread.sleep(3000);
            log.info("<2>");
        } catch (InterruptedException e) {

        }
    }

}
