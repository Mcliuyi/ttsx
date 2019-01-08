package com.ly.servlet;

import com.ly.bean.Order;
import com.ly.load.OrderInfoLoad;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class OrderInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderInfoLoad orderInfoLoad = new OrderInfoLoad(request, response);

        ArrayList<Order> orderArrayList;
        //加载用户信息
        orderArrayList = orderInfoLoad.queryOrderInfo();
        request.setAttribute("orderArrayList", orderArrayList);

        //转发请求
        request.getRequestDispatcher("user_center_order.jsp").forward(request,response);


    }
}
