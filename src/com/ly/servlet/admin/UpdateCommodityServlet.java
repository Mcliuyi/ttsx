package com.ly.servlet.admin;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityType;
import com.ly.dao.CommodityDao;
import com.ly.load.admin.AddCommodityLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class UpdateCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询商品信息
        String id = request.getParameter("id");
        String filed = "id";

        Commodity commodity;
        CommodityDao commodityDao = new CommodityDao();
        AddCommodityLoad addCommodityLoad = new AddCommodityLoad(request, response);

        ArrayList<CommodityType> commodityTypeArrayList;

        try {
            commodity = commodityDao.query(filed, id);
            request.setAttribute("commodity", commodity);

            commodityTypeArrayList = addCommodityLoad.queryType();
            //添加到request
            request.setAttribute("commodityTypeArrayList", commodityTypeArrayList);


            request.getRequestDispatcher("updateCommodity.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
