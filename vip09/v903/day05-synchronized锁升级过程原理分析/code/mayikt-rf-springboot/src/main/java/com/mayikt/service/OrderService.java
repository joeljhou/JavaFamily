package com.mayikt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-06-02 16:44
 */
@RestController
@Slf4j
public class OrderService {

    @Async
    public void asyncLog(){
        try {
            Thread.sleep(3000);
            log.info("<2>");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
