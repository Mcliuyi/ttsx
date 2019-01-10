package com.ly.servlet.admin;

import com.ly.bean.CommodityType;
import com.ly.dao.CommodityTypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class CommodityTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<CommodityType> commodityTypeArrayList;

        CommodityTypeDao commodityTypeDao = new CommodityTypeDao();

        try {
            commodityTypeArrayList = commodityTypeDao.queryAll();

            request.setAttribute("commodityTypeArrayList", commodityTypeArrayList);

            request.getRequestDispatcher("typelist.type").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
