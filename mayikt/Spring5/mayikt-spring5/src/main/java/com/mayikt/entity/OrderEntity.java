package com.mayikt.entity;

/**
 * @author 周宇
 * @create 2021-07-23 17:41
 */
public class OrderEntity {
    private String orderId;
    private String orderName;

    //public OrderEntity() {
    //}

    public OrderEntity(String orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
        System.out.println("反射机制执行到，"+orderId+":"+orderName);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
