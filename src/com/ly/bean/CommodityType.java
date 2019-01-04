package com.ly.bean;

import java.util.ArrayList;

public class CommodityType {
    private int id;
    private String name;
    private int idDel=0;
    private String className;
    private String img;
    private ArrayList<Commodity> commodityArrayList;


    public ArrayList<Commodity> getCommodityArrayList() {
        return commodityArrayList;
    }

    public void setCommodityArrayList(ArrayList<Commodity> commodityArrayList){
        this.commodityArrayList = commodityArrayList;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDel() {
        return idDel;
    }

    public void setIdDel(int idDel) {
        this.idDel = idDel;
    }
}
