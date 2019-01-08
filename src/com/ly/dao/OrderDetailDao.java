package com.ly.dao;

import com.ly.bean.CommodityExtent;
import com.ly.bean.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDao extends BaseDao {


    /**
     * 添加订单详细信息
     * @param orderDetail 订单详细实体类
     * @return true or false
     * @throws SQLException
     */
    public Boolean add(OrderDetail orderDetail) throws SQLException {
        Boolean rlt;

        this.sql = "insert into order_detail(id, cid, num) values(?, ?, ?)";

        rlt = this.jdbCutil.update(this.sql, orderDetail.getId(), orderDetail.getCid(), orderDetail.getNum());

        return  rlt;
    }


}
