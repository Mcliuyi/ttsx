package com.ly.dao;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityType;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommodityTypeDao extends BaseDao {

    /**
     * 查询所有商品类型
     * @return 返回商品类型对象列表
     * @throws SQLException
     */
    public ArrayList<CommodityType> queryAll() throws SQLException {
        ArrayList<CommodityType> commodityTypeArrayList = new ArrayList<CommodityType>();
        CommodityType commodityType;
        this.sql = "select id, type, is_del from commodity_type";
        this.rs = this.jdbCutil.query(this.sql);
        while (this.rs.next()){
            commodityType = new CommodityType();
            commodityType.setId(this.rs.getInt("id"));
            commodityType.setName(this.rs.getString("type"));
            commodityType.setIdDel(this.rs.getInt("is_del"));
            commodityTypeArrayList.add(commodityType);
        }
        return commodityTypeArrayList;
    }

    /**
     * 通过商品类型id查询单个商品类型信息
     * @param id 商品类型id
     * @return commodityType 商品类型对象
     * @throws SQLException
     */
    public CommodityType query(int id) throws SQLException {
        CommodityType commodityType = new CommodityType();
        this.sql = "select id, type, is_del from commodity_type where id=?";
        this.rs = this.jdbCutil.query(this.sql, id);
        while (this.rs.next()){
            commodityType.setName(this.rs.getString("type"));
            commodityType.setId(this.rs.getInt("id"));
            commodityType.setIdDel(this.rs.getInt("is_del"));
        }

        return commodityType;
    }

    /**
     * 添加商品类型
     * @param commodityType 商品类型对象
     * @return true or flase
     * @throws SQLException
     */
    public Boolean add(CommodityType commodityType) throws SQLException {
        Boolean rlt;
        this.sql = "insert into commodity_type(type, is_del) values(?, ?)";
        rlt = this.jdbCutil.update(this.sql, commodityType.getName(), commodityType.getIdDel());
        return rlt;
    }

}
