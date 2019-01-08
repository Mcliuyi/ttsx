package com.ly.dao;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityExtent;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommodityDao extends BaseDao{

    /**
     * 通过商品某样信息查询商品
     * @param info 商品info
     * @param field 查询字段
     * @return 商品实体类
     */
    public Commodity query(String field, String info) throws SQLException {
        Commodity commodity = new Commodity();
        this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, click_num from commodity where " + field +  " = ? ";
        this.rs = this.jdbCutil.query(sql, info);
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
            commodity.setClick_num(rs.getInt("click_num"));
        }
        return commodity;
    }

    /**
     * 通过商品类型id获取对应类型商品
     * @param fieldContent 查询内容
     * @param page 从第几个开始
     * @param num 查找多少个
     * @param sort 排序规则 0 不排序 1 升序 2降序
     * @param ruleField 排序字段
     * @param blurry 是否模糊查询 0 否 1 是
     * @return
     * @throws SQLException
     */
    public ArrayList<Commodity> query(String fieldContent, int page, int num, int sort, String ruleField, int blurry, String blurrFiedl) throws SQLException {
        ArrayList<Commodity> commodityArrayList = new ArrayList<Commodity>();
        Commodity commodity;
        if(sort == 0 && blurry == 0){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time, click_num from commodity where tid = ? limit ?, ?";

        }else if(sort == 1 && blurry == 0){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time, click_num from commodity where tid = ? order by "+ ruleField + " limit ?, ?";
            //this.rs = this.jdbCutil.query(sql, id, ruleField, page, num);
        }else if(sort == 2  && blurry == 0 ){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time, click_num from commodity where tid = ? order by "+ ruleField +" desc limit ?, ?";
            //this.rs = this.jdbCutil.query(sql, id, page, num);
        }else if(sort == 0 && blurry == 1){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time, click_num from commodity where commodity_name like ? limit ?, ?";
        }else if(sort == 1 && blurry == 1){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time, click_num from commodity where commodity_name like ? order by "+ ruleField + " limit ?, ?";
        }else if(sort == 2 && blurry == 1){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time, click_num from commodity where commodity_name like ? order by "+ ruleField +" desc limit ?, ?";
        }
        //代表查询两个最新发布的商品
        if("0".equals(fieldContent) && blurrFiedl == null){
            this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, create_time, click_num from commodity  order by "+ ruleField +" desc limit ?, ?";
            this.rs = this.jdbCutil.query(sql, page, num);
        }else if(blurrFiedl == null){
            this.rs = this.jdbCutil.query(sql, fieldContent, page, num);
        }else {
            this.rs = this.jdbCutil.query(sql, blurrFiedl, page, num);
        }

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
            commodity.setClick_num(rs.getInt("click_num"));
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
    }

    /**
     * 模糊查询商品一共有多少数据
     * @param blurrFiedl 约束条件
     * @return 数量
     * @throws SQLException
     */
    public int query(String blurrFiedl) throws SQLException {
        int total = 0;
        this.sql = "select count(*) from commodity where is_del=0 and commodity_name like ? ";
        this.rs = this.jdbCutil.query(this.sql, blurrFiedl);
        while (rs.next()){
            total = rs.getInt(1);
        }
        return total;
    }

    /**
     * 查询商品信息并存入到商品扩展类
     * @param field
     * @param info
     * @return
     * @throws SQLException
     */
    public CommodityExtent queryExtent(String  field, String info) throws SQLException {
        CommodityExtent commodityExtent = new CommodityExtent();
        Commodity commodity = new Commodity();
        this.sql  = "select id, commodity_name, price, unit, image, introduction, commodity_info, num, tid, click_num from commodity where " + field +  " = ? ";
        this.rs = this.jdbCutil.query(sql, info);
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
            commodity.setClick_num(rs.getInt("click_num"));
            commodityExtent.setCommodity(commodity);
        }

        return commodityExtent;
    }


    /**
     * 查询商品信息并存入到商品扩展类
     * @param oid 订单id
     * @return
     * @throws SQLException
     */
    public ArrayList<CommodityExtent>  queryOrderCommodiyt(int oid) throws SQLException {
        ArrayList<CommodityExtent> commodityExtentArrayList = new ArrayList<CommodityExtent>();
        CommodityExtent commodityExtent = new CommodityExtent();
        Commodity commodity = new Commodity();
        this.sql  = "select c.id, c.commodity_name, c.price, c.unit, c.image, o.num from commodity as c inner join order_detail as o  on c.id = o.cid and o.id = ? ";
        this.rs = this.jdbCutil.query(sql, oid);
        while (rs.next()){
            commodity.setId(rs.getInt("id"));
            commodity.setCommodity_name(rs.getString("commodity_name"));
            commodity.setPrice(rs.getDouble("price"));
            commodity.setUnit(rs.getString("unit"));
            commodity.setImg(rs.getString("image"));
            commodityExtent.setNum(rs.getInt("num"));
            commodityExtent.setCommodity(commodity);
            commodityExtentArrayList.add(commodityExtent);
        }

        return commodityExtentArrayList;
    }



    /**
     * 通过用户id查询购物车中的商品信息
     * @param uid 用户id
     * @return 商品扩展类数组
     * @throws SQLException
     */
    public ArrayList<CommodityExtent> queryShopCart(int uid) throws SQLException {
        CommodityExtent commodityExtent;
        Commodity commodity;
        ArrayList<CommodityExtent> commodityExtentArrayList = new ArrayList<CommodityExtent>();
        this.sql  = "select s.id, s.commodity_name, s.price, s.unit, s.image, s.introduction, s.commodity_info, s.num, s.tid, s.click_num, c.num as n from cart as c inner join commodity as s\n" +
                "on c.cid = s.id and c.uid = ?; ";
        this.rs = this.jdbCutil.query(sql, uid);
        while (rs.next()){
            commodityExtent = new CommodityExtent();
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
            commodity.setClick_num(rs.getInt("click_num"));
            commodityExtent.setCommodity(commodity);
            commodityExtent.setNum(rs.getInt("n"));
            commodityExtentArrayList.add(commodityExtent);
        }
        return commodityExtentArrayList;
    }



    /**
     * 添加商品信息
     * @param commodity 商品对象
     * @return true or false
     * @throws SQLException
     */
    public Boolean add(Commodity commodity) throws SQLException {
        Boolean rlt;
        this.sql = "insert into commodity(commodity_name, price, unit, image, introduction, commodity_info, num, tid, is_del, create_time, click_num) values(?,?,?,?,?,?,?,?,?,?,?)";
        rlt = this.jdbCutil.update(this.sql, commodity.getCommodity_name(),
                commodity.getPrice(), commodity.getUnit(), commodity.getImg(), commodity.getIntroduction(), commodity.getCommodity_info(),
                commodity.getNum(), commodity.getTid(), commodity.getIs_del(), commodity.getCreateTime(), commodity.getClick_num());

        return rlt;
    }

    /**
     * 通过商品id修改商品信息
     * @param commodity 商品实体类对象
     * @return true or false
     */
    public Boolean update(Commodity commodity) throws SQLException {
        Boolean rlt;
        this.sql = "update commodity set commodity_name=?, price=?, unit=?, image=?, introduction=?, commodity_info=?, num=?, tid=?, is_del=?, click_num = ? where id=?";
        rlt = this.jdbCutil.update(this.sql, commodity.getCommodity_name(),
                commodity.getPrice(), commodity.getUnit(), commodity.getImg(), commodity.getIntroduction(), commodity.getCommodity_info(),
                commodity.getNum(), commodity.getTid(), commodity.getIs_del(), commodity.getClick_num(), commodity.getId());


        return rlt;
    }





}
