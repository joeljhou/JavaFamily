package com.mayikt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-06-03 10:20
 */
@RestController
@Slf4j
@Scope(value = "prototype")
public class CountService {

    private int count = 0;

    @RequestMapping("/count")
    public synchronized String count(){
        try {
            log.info(">count<" + count++);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        } catch (Exception e) {
        }
        return "count";
    }

}
