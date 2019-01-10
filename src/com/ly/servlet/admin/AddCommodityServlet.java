package com.ly.servlet.admin;

import com.ly.bean.CommodityType;
import com.ly.load.admin.AddCommodityLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class AddCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AddCommodityLoad addCommodityLoad = new AddCommodityLoad(request, response);
        //添加数据
        addCommodityLoad.add();
        response.sendRedirect("list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AddCommodityLoad addCommodityLoad = new AddCommodityLoad(request, response);

        ArrayList<CommodityType> commodityTypeArrayList;

        try {
            commodityTypeArrayList = addCommodityLoad.queryType();
            //添加到request
            request.setAttribute("commodityTypeArrayList", commodityTypeArrayList);
            request.getRequestDispatcher("addCommodity.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
