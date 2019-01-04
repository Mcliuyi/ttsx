package com.ly.servlet;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityType;
import com.ly.dao.CommodityDao;
import com.ly.dao.CommodityTypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet(name = "CommodityDetailServlet")
public class CommodityDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String field = "id";
        Commodity commodity;
        CommodityDao commodityDao = new CommodityDao();
        //商品类型数据
        CommodityType commodityType;
        CommodityTypeDao commodityTypeDao = new CommodityTypeDao();
        try {
            //查询商品信息
            commodity = commodityDao.query(field, id);
            request.setAttribute("commodity", commodity);

            //查询商品类型数据
            commodityType = commodityTypeDao.query(commodity.getTid());
            request.setAttribute("commodityType", commodityType);

            request.getRequestDispatcher("detail.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            commodityDao.close();
            commodityTypeDao.close();
        }

    }
}
