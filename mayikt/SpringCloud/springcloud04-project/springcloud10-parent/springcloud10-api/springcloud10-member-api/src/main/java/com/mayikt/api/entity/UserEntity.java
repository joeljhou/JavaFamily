package com.mayikt.api.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 周宇
 * @create 2021-07-20 22:43
 */
@Data
@Accessors(chain = true) //静态链式编程
public class UserEntity {

    private String name;
    private Integer age;

}
