package com.mayiky.controller;

import com.alibaba.fastjson.JSONObject;
import com.mayiky.event.OrderCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-29 0:04
 */
@RestController
@Slf4j
public class UserCntroller {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping("/addOrder")
    public String  addOrder(){
        //组装消息内容
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email","2371425251@qq.com");
        jsonObject.put("phone","1857669412x");
        jsonObject.put("text","恭喜获得200元的蚂蚁课堂vip");

        OrderCreateEvent messageEntity = new OrderCreateEvent(this, jsonObject);
        applicationEventPublisher.publishEvent(messageEntity);
        return "success";
    }

}
