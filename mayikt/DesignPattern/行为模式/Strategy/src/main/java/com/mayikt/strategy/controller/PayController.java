package com.mayikt.strategy.controller;

import com.mayikt.strategy.context.PayContextStrategy;
import com.mayikt.strategy.enumeration.PayEnumStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-29 0:56
 */
@RestController
public class PayController {

    @Autowired
    private PayContextStrategy payContextStrategy;

    @RequestMapping("/toPayHtml")
    public String toPayHtml(String payCode){
        return payContextStrategy.toPayHtml(payCode);
    }


}
