package com.mayikt.service;

import com.mayikt.service.manage.OrderManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouyu
 * @create 2021-05-27 3:30
 */
@RestController
@Slf4j
public class MemberService {

    @Autowired
    private OrderManage orderManage;

    @RequestMapping("/addOrder")
    public String addOrder(){
        log.info("<1>");
        orderManage.asyncLog();
        log.info("<3>");
        return "3";
    }

}
