package com.ly.load;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityExtent;
import com.ly.bean.Order;
import com.ly.bean.User;
import com.ly.dao.CommodityDao;
import com.ly.dao.OrderDao;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderInfoLoad extends BaseLoad {
    public OrderInfoLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }


    /**
     * 查询用户订单信息
     */
    public ArrayList<Order> queryOrderInfo(){
        User user;
        ArrayList<Order> orderArrayList = new ArrayList<Order>();
        ArrayList<CommodityExtent> commodityExtentArrayList;
        HttpSession session = request.getSession();

        user = (User) session.getAttribute("user");
        //获取当前页数
        int page ;
        int limit = 2;
        int t_page = 0; //总页数
        int o_num = 0;
        String p = request.getParameter("page");
        if( p == null || "".equals(p)){
            page = 1;
        }else {
            page = Integer.parseInt(request.getParameter("page"));
        }

        OrderDao orderDao = new OrderDao();
        CommodityDao commodityDao = new CommodityDao();


        try {
            //查询用户订单数量
            o_num = orderDao.query(user.getId());
            //总页数
            t_page = (int)Math.ceil( (double)o_num / limit);
            //存入
            request.setAttribute("o_page", t_page);
            request.setAttribute("page", page);
            //查询用户对应订单信息
            orderArrayList = orderDao.query(user.getId(), (page - 1)* limit, limit);
            //查询订单对应的商品信息
            for (Order order:orderArrayList) {
                commodityExtentArrayList = commodityDao.queryOrderCommodiyt(order.getId());
                //将查询出来的商品列表存入订单中
                order.setCommodityExtentArrayList(commodityExtentArrayList);
            }

        } catch (SQLException e) {
            System.out.println("查询用户订单信息失败");
            e.printStackTrace();
        }finally {
            orderDao.close();
            commodityDao.close();
        }

        return orderArrayList;
    }

}
