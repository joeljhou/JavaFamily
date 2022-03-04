package com.mayikt.controller;

import com.google.common.collect.Lists;
import com.mayikt.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 周宇
 * @create 2021-07-13 1:30
 */
@Controller
public class ThymeleafIndexController {

    @RequestMapping("/thymeleafIndex")
    public String thymeleafIndex(HttpServletRequest request, Map<String, Object> result) {
        //request.setAttribute("user",new UserEntity("mayikt", 22));
        //解决thymeleaf误报问题
        result.put("user", new UserEntity("mayikt", 22));

        List<UserEntity> userList = Lists.newArrayList(new UserEntity("mayikt1", 22),
                new UserEntity("mayikt2", 22),
                new UserEntity("mayikt3", 22));
        result.put("userList",userList);

        return "thymeleafIndex";
    }

}
