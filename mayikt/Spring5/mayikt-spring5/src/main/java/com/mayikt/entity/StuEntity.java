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
    //1.数组属性
    private String[] arrays;
    //2.list集合属性
    private List<String> list;
    //3.Map
    private Map<String,String> map;
    //4.Set
    private Set<String> set;

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

    @Override
    public String toString() {
        return "StuEntity{" +
                "arrays=" + Arrays.toString(arrays) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                '}';
    }
}
