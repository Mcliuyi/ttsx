package com.ly.dao;

import com.ly.bean.Commodity;
import com.ly.bean.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args){

        Commodity commodity = new Commodity();
        String name = "吃着吃太空蚌长大的秋刀鱼的超级虾";
        double price = 1100000.0;
        String unit = "250g";
        String img = "images/goods/goods21.jpg";
        String introduction = "生鲜平台哪家强，中国湖南找吉首，近百台世界各地先进设备供学员实习，试学一月不收任何费用，天天生鲜平台";
        String commodity_info = "新鲜健康的好东西，买不了吃亏，买不了上当";
        int num = 100;
        int tid = 6;
        commodity.setCommodity_name(name);
        commodity.setCommodity_info(commodity_info);
        commodity.setTid(tid);
        commodity.setNum(num);
        commodity.setImg(img);
        commodity.setPrice(price);
        commodity.setUnit(unit);
        commodity.setIntroduction(introduction);
        CommodityDao commodityDao = new CommodityDao();
        try {
            System.out.println(commodityDao.add(commodity));
            //commodity = commodityDao.query("id", 1);
            //System.out.println("商品名：" + commodity.getCommodity_name());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
