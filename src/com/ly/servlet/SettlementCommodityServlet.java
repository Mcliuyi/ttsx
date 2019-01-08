package com.ly.servlet;

import com.ly.bean.*;
import com.ly.load.AddrLoad;
import com.ly.load.SettlementCommodityLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SettlementCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SettlementCommodityLoad settlementCommodityLoad = new SettlementCommodityLoad(request, response);
        ArrayList<PlaceOrder>  placeOrderArrayList;
        ArrayList<CommodityExtent> commodityExtentArrayList;
        HttpSession session = request.getSession();
        //获取数据
        placeOrderArrayList = settlementCommodityLoad.settlementOrder();

        //根据请求的数据查询商品信息
        commodityExtentArrayList = settlementCommodityLoad.queryCommodityInfo(placeOrderArrayList);

        //商品信息存入放入请求参数
        session.setAttribute("commodityExtentArrayList", commodityExtentArrayList);

        //设置订单对象信息
        OrderInfo orderInfo;
        orderInfo = settlementCommodityLoad.setOrderInfo(commodityExtentArrayList);



        //获取地址信息
        AddrLoad addrLoad = new AddrLoad(request, response);
        Adress adress;
        adress = addrLoad.queryAddr();

        //存入地址信息
        session.setAttribute("adress", adress);

        User user = (User) session.getAttribute("user");

        //创建订单
        try {
            Order order = settlementCommodityLoad.createOrder(user.getId(), orderInfo.getPrice());
            //创建订单详细
            settlementCommodityLoad.createOrderDetail(commodityExtentArrayList, order.getId());
            //设置订单id
            orderInfo.setId(order.getId());
            //订单信息存入
            session.setAttribute("orderInfo", orderInfo);
        } catch (SQLException e) {
            System.out.println("创建订单时错误");
            e.printStackTrace();
        }



        //删除提交订单内的商品信息
        try {
            settlementCommodityLoad.delShopCart(commodityExtentArrayList, user.getId());
        } catch (SQLException e) {
            System.out.println("删除提交订单内的商品信息失败");
            e.printStackTrace();
        }

        //response.sendRedirect("place_order.jsp");
        response.getWriter().print("1");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





    }
}
