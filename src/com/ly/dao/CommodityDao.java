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
            commodity.setPrice(rs.getDouble("price"));
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
     * @param page 从第几个开始
     * @param num 查找多少个
     * @param sort 排序规则 0 不排序 1 升序 2降序
     * @param ruleField 排序字段
     * @return
     * @throws SQLException
     */
    public ArrayList<Commodity> query(int id, int page, int num, int sort, String ruleField) throws SQLException {
        ArrayList<Commodity> commodityArrayList = new ArrayList<Commodity>();
        Commodity commodity;
        if(sort == 0){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time from commodity where tid = ? limit ?, ?";

        }else if(sort == 1){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time from commodity where tid = ? order by "+ ruleField + " limit ?, ?";
            //this.rs = this.jdbCutil.query(sql, id, ruleField, page, num);
        }else if(sort == 2){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time from commodity where tid = ? order by "+ ruleField +" desc limit ?, ?";
            //this.rs = this.jdbCutil.query(sql, id, page, num);
        }
        this.rs = this.jdbCutil.query(sql, id, page, num);
        while (rs.next()){
            commodity = new Commodity();
            commodity.setId(rs.getInt("id"));
            commodity.setCommodity_name(rs.getString("commodity_name"));
            commodity.setPrice(rs.getDouble("price"));
            commodity.setUnit(rs.getString("unit"));
            commodity.setImg(rs.getString("image"));
            commodity.setIntroduction(rs.getString("introduction"));
            commodity.setCommodity_info(rs.getString("commodity_info"));
            commodity.setNum(rs.getInt("num"));
            commodity.setTid(rs.getInt("tid"));
            commodity.setCreateTime(rs.getString("create_time"));
            commodityArrayList.add(commodity);
        }


        return commodityArrayList;
    }

    /**
     * 查询某一类型商品一共有多少数据
     * @param tid 类型id
     * @return 数量
     * @throws SQLException
     */
    public int query(int tid) throws SQLException {
        int total = 0;
        this.sql = "select count(*) from commodity where is_del=0 and tid = ?";
        this.rs = this.jdbCutil.query(this.sql, tid);
        while (rs.next()){
            total = rs.getInt(1);
        }
        return total;
    };


    /**
     * 添加商品信息
     * @param commodity 商品对象
     * @return true or false
     * @throws SQLException
     */
    public Boolean add(Commodity commodity) throws SQLException {
        Boolean rlt;
        this.sql = "insert into commodity(commodity_name, price, unit, image, introduction, commodity_info, num, tid, is_del, create_time) values(?,?,?,?,?,?,?,?,?,?)";
        rlt = this.jdbCutil.update(this.sql, commodity.getCommodity_name(),
                commodity.getPrice(), commodity.getUnit(), commodity.getImg(), commodity.getIntroduction(), commodity.getCommodity_info(),
                commodity.getNum(), commodity.getTid(), commodity.getIs_del(), commodity.getCreateTime());

        return rlt;
    }

    /**
     * 通过商品id修改商品信息
     * @param commodity 商品实体类对象
     * @return true or false
     */
    public Boolean update(Commodity commodity) throws SQLException {
        Boolean rlt;
        this.sql = "update commodity set commodity_name=?, price=?, unit=?, image=?, introduction=?, commodity_info=?, num=?, tid=?, is_del=? where id=?";
        rlt = this.jdbCutil.update(this.sql, commodity.getCommodity_name(),
                commodity.getPrice(), commodity.getUnit(), commodity.getImg(), commodity.getIntroduction(), commodity.getCommodity_info(),
                commodity.getNum(), commodity.getTid(), commodity.getIs_del(), commodity.getId());


        return rlt;
    }





}
