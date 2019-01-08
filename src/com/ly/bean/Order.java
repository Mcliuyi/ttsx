package com.ly.bean;

import com.ly.util.Config;

import java.util.ArrayList;

public class Order {
    private int id;
    private int uid;
    private String createtime;
    private double price;
    private int status = 0;//0 未支付， 1 已支付
    private ArrayList<CommodityExtent> commodityExtentArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {

        this.createtime = createtime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<CommodityExtent> getCommodityExtentArrayList() {
        return commodityExtentArrayList;
    }

    public void setCommodityExtentArrayList(ArrayList<CommodityExtent> commodityExtentArrayList) {
        this.commodityExtentArrayList = commodityExtentArrayList;
    }
}
