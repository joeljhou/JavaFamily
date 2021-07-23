package com.mayikt.entity;

/**
 * @author 周宇
 * @create 2021-07-23 22:09
 * 课程
 */
    public class CourseEntity {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
