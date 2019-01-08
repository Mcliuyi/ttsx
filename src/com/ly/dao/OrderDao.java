package com.ly.dao;

import com.ly.bean.Order;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao extends BaseDao {


    /**
     * 添加订单信息
     * @param order 订单对象
     * @return true or false
     * @throws SQLException
     */
    public Boolean add(Order order) throws SQLException {
        Boolean rlt;
        this.sql = "insert into orders(id, uid, create_time, price, status) values(?,?,?,?,?)";
        rlt = this.jdbCutil.update(this.sql, order.getId(), order.getUid(), order.getCreatetime(), order.getPrice(), order.getStatus());
        return rlt;
    }

    /**
     * 查询用户订单信息
     * @param uid 用户id
     * @return
     * @throws SQLException
     */
    public ArrayList<Order> query(int uid, int page, int limit) throws SQLException {
        ArrayList<Order> orderArrayList = new ArrayList<Order>();
        Order order;
        this.sql  = "select id, uid, create_time, price, status from orders where uid=? limit ?,?";
        this.rs = this.jdbCutil.query(this.sql, uid, page, limit);
        while (this.rs.next()){
            order = new Order();
            order.setId(this.rs.getInt("id"));
            order.setUid(this.rs.getInt("uid"));
            order.setCreatetime(this.rs.getString("create_time"));
            order.setPrice(this.rs.getDouble("price"));
            order.setStatus(this.rs.getInt("status"));
            orderArrayList.add(order);
        }

        return orderArrayList;
    }


    /**
     * 查询用户订单数量
     * @param uid 用户id
     * @return
     * @throws SQLException
     */
    public int query(int uid) throws SQLException {

        int num = 0;
        this.sql  = "select count(*) from orders where uid=?";
        this.rs = this.jdbCutil.query(this.sql, uid);
        while (this.rs.next()){
           num = this.rs.getInt(1);
        }

        return num;
    }

}
