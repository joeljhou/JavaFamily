package com.mayikt.entity;

import lombok.Data;

/**
 * @author 周宇
 * @create 2021-07-13 1:35
 */
@Data
public class UserEntity {
    private String userName;
    private Integer age;

    public UserEntity(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

}
