package com.mayikt.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author 周宇
 * @create 2021-07-12 14:01
 */
@Controller
public class FreemarkerIndexController {

    @RequestMapping ("/freemarkerIndex")
    public String freemarkerIndex(Map<String,Object> result){
        result.put("name","mayikt");
        result.put("sex","0");
        result.put("userlist", Lists.newArrayList("zhangsan", "lisi", "mayikt"));

        return "freemarkerIndexPage";//跳转的模板页面
    }

}
