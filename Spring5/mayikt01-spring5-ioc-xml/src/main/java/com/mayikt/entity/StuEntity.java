package com.mayikt.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 周宇
 * @create 2021-07-23 22:00
 */
public class StuEntity {
    /**
     * 1.数组属性
     * 2.list集合属性
     * 3.Map
     * 4.Set
     */
    private String[] arrays;
    private List<String> list;
    private Map<String,String> map;
    private Set<String> set;

    private List<CourseEntity> courseEntities;

    public void setArrays(String[] arrays) {
        this.arrays = arrays;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setCourseEntities(List<CourseEntity> courseEntities) {
        this.courseEntities = courseEntities;
    }

    @Override
    public String toString() {
        return "StuEntity{" +
                "arrays=" + Arrays.toString(arrays) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", courseEntities=" + courseEntities +
                '}';
    }
}
