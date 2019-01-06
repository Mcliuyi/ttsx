package com.ly.dao;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityExtent;
import com.ly.bean.ShopCart;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShopCartDao extends BaseDao{





    /**
     * 添加商品至购物车
     * @param cid 商品id
     * @param id 用户id
     * @param num 商品数量
     * @return true or false
     */
    public Boolean add(int cid, int num, int id) throws SQLException {
        Boolean rlt;

        this.sql = "insert into cart(uid, cid, num) values(?, ?, ?)";

        rlt = this.jdbCutil.update(this.sql, id, cid, num);

        return rlt;
    }

    /**
     * 查询用户购物车商品数量
     * @param uid 用户id
     * @return 商品数量
     * @throws SQLException
     */
    public int query(int uid) throws SQLException {
        int num = 0;
        this.sql = "select sum(num) from cart where uid=?";
        this.rs = this.jdbCutil.query(this.sql, uid);
        while (this.rs.next()){
            num = this.rs.getInt(1);
        }

        return num;
    }

    /**
     * 查询购车某样商品的数量
     * @param uid 用户id
     * @param cid 商品id
     * @return 数量 int
     * @throws SQLException
     */
    public int  query(int uid, int cid) throws SQLException {
        int num = 0;
        this.sql = "select num from cart where uid=? and cid=?";
        this.rs = this.jdbCutil.query(this.sql, uid, cid);
        while (this.rs.next()){
            num = this.rs.getInt("num");
        }

        return num;
    }

    /**
     * 修改某样商品数量
     * @param uid
     * @param cid
     * @return
     * @throws SQLException
     */
    public Boolean update(int num, int uid, int cid) throws SQLException {
        Boolean rlt;

        this.sql = "update cart set num=? where uid=? and cid=?";
        rlt = this.jdbCutil.update(this.sql, num, uid, cid);

        return rlt;

    }


    /**
     * 判断添加到购物车的商品是否已存在
     * @param uid 用户id
     * @param cid 商品id
     * @return true or false
     * @throws SQLException
     */
    public Boolean queryExist(int uid, int cid) throws SQLException {
        Boolean rlt;

        this.sql = "select count(*) from cart where uid=? and cid=?";
        this.rs = this.jdbCutil.query(this.sql, uid, cid);
        int num=0;
        while (this.rs.next()){
            num = this.rs.getInt(1);
        }
        if(num > 0){
            rlt = true;
        }else {rlt=false;}

        return rlt;
    }

    /**
     * 删除购物车某样商品
     * @param uid 用户id
     * @param cid 商品id
     * @return
     */
    public  Boolean del(int uid, int cid) throws SQLException {
        Boolean rlt;

        this.sql = "delete from cart where uid=? and cid=?";
        rlt = this.jdbCutil.update(this.sql, uid, cid);

        return rlt;
    }

}
