package com.mayiky.event.listener;

import com.mayiky.event.OrderCreateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-29 0:19
 */
@Component
public class SmsListener implements ApplicationListener<OrderCreateEvent> {
    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        System.out.println("开始发送短信消息内容:"+ event.getJsonObject().toJSONString());
    }
}
