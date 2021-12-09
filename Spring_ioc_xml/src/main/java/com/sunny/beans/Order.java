package com.sunny.beans;

public class Order {

    private int orderId;
    private String orderName;

    public Order() {
        System.out.println("Order 加载");
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
