package com.mayikt.controller;

import com.mayikt.factory.TemplateFactory;
import com.mayikt.template.AbstractPayCallbackTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-29 2:21
 */
@RestController
public class TemplateController {

    /**
     * 支付回调
     */
    @RequestMapping("/asyncCallBack")
    public String asyncCallBack(String templateId){
        AbstractPayCallbackTemplate payCallbackTemplate = TemplateFactory.getPayCallbackTemplate(templateId);
        //使用模板方法模式 执行共同的骨架
        return payCallbackTemplate.asyncCallBack();
    }

}
