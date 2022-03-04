package com.mayikt.factory;

import com.mayikt.template.AbstractPayCallbackTemplate;
import com.mayikt.utils.SpringUtils;

/**
 * @author 周宇
 * @create 2021-07-29 2:11
 */
public class TemplateFactory {

    /**
     * 使用工厂模式获取模板
     */
    public static AbstractPayCallbackTemplate getPayCallbackTemplate(String templateId){
        AbstractPayCallbackTemplate payCallbackTemplate = (AbstractPayCallbackTemplate) SpringUtils.getBean(templateId);
        return payCallbackTemplate;
    }
}
