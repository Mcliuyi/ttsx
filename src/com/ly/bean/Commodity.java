package com.ly.bean;

import com.ly.util.Config;

public class Commodity {
    private int id;
    private String commodity_name;
    private double price;
    private String unit;
    private String img;
    private String introduction;
    private String commodity_info;
    private int num;
    private int tid;
    private int is_del = 0;
    private String createTime;
    private int click_num = 0;

    public int getClick_num() {
        return click_num;
    }

    public void setClick_num(int click_num) {
        this.click_num = click_num;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        if (createTime == null){
            this.createTime = Config.getTime();
        }else {
            this.createTime = createTime;
        }


    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCommodity_info() {
        return commodity_info;
    }

    public void setCommodity_info(String commodity_info) {
        this.commodity_info = commodity_info;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
