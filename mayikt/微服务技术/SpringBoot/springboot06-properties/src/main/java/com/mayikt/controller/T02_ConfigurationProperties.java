package com.mayikt.controller;

import com.mayikt.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-14 0:14
 * //02. 多个属性批量配置
 */
@RestController
public class T02_ConfigurationProperties {

    @Autowired
    private UserEntity mayiktUserEntity;

    @RequestMapping("/getNameAndAgeAddres")
    public String getNameAndAgeAddres() {
        return mayiktUserEntity.toString();
    }

}
