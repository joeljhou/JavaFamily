package com.mayikt.entity;

/**
 * @author 周宇
 * @create 2021-07-23 19:31
 */
public class DeptEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeptEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
