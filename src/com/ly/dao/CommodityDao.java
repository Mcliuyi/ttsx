package com.ly.dao;

import com.ly.bean.Commodity;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommodityDao extends BaseDao{

    /**
     * 通过商品id查询商品
     * @param id 商品id
     * @return 商品实体类
     */
    public Commodity query(String field, int id) throws SQLException {
        Commodity commodity = new Commodity();
        this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid from commodity where " + field +  " = ? ";
        this.rs = this.jdbCutil.query(sql, id);
        while (rs.next()){
            commodity.setId(rs.getInt("id"));
            commodity.setCommodity_name(rs.getString("commodity_name"));
            commodity.setPrice(rs.getInt("price"));
            commodity.setUnit(rs.getString("unit"));
            commodity.setImg(rs.getString("image"));
            commodity.setIntroduction(rs.getString("introduction"));
            commodity.setCommodity_info(rs.getString("commodity_info"));
            commodity.setNum(rs.getInt("num"));
            commodity.setTid(rs.getInt("tid"));
        }
        return commodity;
    }

    /**
     * 通过商品类型id获取对应类型商品
     * @param id 商品类型id
     * @return
     * @throws SQLException
     */
    public ArrayList<Commodity> query(int id) throws SQLException {
        ArrayList<Commodity> commodityArrayList = new ArrayList<Commodity>();
        Commodity commodity;
        this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid from commodity where tid = ? ";
        this.rs = this.jdbCutil.query(sql, id);
        while (rs.next()){
            commodity = new Commodity();
            commodity.setId(rs.getInt("id"));
            commodity.setCommodity_name(rs.getString("commodity_name"));
            commodity.setPrice(rs.getInt("price"));
            commodity.setUnit(rs.getString("unit"));
            commodity.setImg(rs.getString("image"));
            commodity.setIntroduction(rs.getString("introduction"));
            commodity.setCommodity_info(rs.getString("commodity_info"));
            commodity.setNum(rs.getInt("num"));
            commodity.setTid(rs.getInt("tid"));
            commodityArrayList.add(commodity);
        }


        return commodityArrayList;
    }


    /**
     * 添加商品信息
     * @param commodity 商品对象
     * @return true or false
     * @throws SQLException
     */
    public Boolean add(Commodity commodity) throws SQLException {
        Boolean rlt;
        this.sql = "insert into commodity(commodity_name, price, unit, image, introduction, commodity_info, num, tid, is_del) values(?,?,?,?,?,?,?,?,?)";
        rlt = this.jdbCutil.update(this.sql, commodity.getCommodity_name(),
                commodity.getPrice(), commodity.getUnit(), commodity.getImg(), commodity.getIntroduction(), commodity.getCommodity_info(),
                commodity.getNum(), commodity.getTid(), commodity.getIs_del());

        return rlt;
    }


}
