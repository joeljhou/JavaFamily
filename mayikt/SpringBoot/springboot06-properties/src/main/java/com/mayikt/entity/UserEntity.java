package com.mayikt.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 周宇
 * @create 2021-07-14 0:11
 * //02. 多个属性批量配置
 */
@Data
@Component
@ConfigurationProperties(prefix="mayikt")
public class UserEntity {

    private String name;
    private Integer age;
    private String info;
    private String address;

    @Override
    public String toString() {
        return "T02_ConfigurationProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", info='" + info + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
