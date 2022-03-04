package com.mayiky.event;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationEvent;

/**
 * @author 周宇
 * @create 2021-07-29 0:18
 * 定义消息实体类
 */
public class OrderCreateEvent extends ApplicationEvent {

    /**
     * 群发消息内容
     */
    private JSONObject jsonObject;

    public OrderCreateEvent(Object source, JSONObject jsonObject) {
        super(source);
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
