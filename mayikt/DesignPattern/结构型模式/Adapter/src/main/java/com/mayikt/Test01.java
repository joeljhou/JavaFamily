package com.mayikt;

import com.mayikt.adapter.ListAdapter;
import com.mayikt.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2021-07-30 0:53
 */
public class Test01 {
    public static void main(String[] args) {
        //1.定义源 老版本
        OrderService orderService = new OrderService();
        List<String> arrayList = new ArrayList<>();
        arrayList.add("张三");
        arrayList.add("法外狂徒");
        //使用适配器实现转换
        ListAdapter listAdapter = new ListAdapter(arrayList);
        orderService.forMap(listAdapter);
    }
}
