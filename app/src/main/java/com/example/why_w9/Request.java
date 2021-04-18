package com.example.why_w9;

import java.util.List;

public class Request {
    private String phone;
    private String name;
    private String address;
    private String total;
    private List<Order> foods; // list of food order
    private String uid;
    private String orderType;

    public Request() {
    }

    public Request(String phone, String name, String address, String total, List<Order> foods) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.foods = foods;
    }
    public Request(String uid,String total, List<Order> foods,String orderType)
    {
        this.uid=uid;
        this.total = total;
        this.foods = foods;
        this.orderType=orderType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
