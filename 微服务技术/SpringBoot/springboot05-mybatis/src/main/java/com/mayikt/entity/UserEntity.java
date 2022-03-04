package com.mayikt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周宇
 * @create 2021-07-13 1:35
 */
@Data
@NoArgsConstructor
public class UserEntity {
    private Integer id;
    private String userName;
    private Integer age;

    public UserEntity(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

}
