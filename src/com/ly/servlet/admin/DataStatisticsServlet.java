package com.ly.servlet.admin;

import com.ly.bean.DataStatistics;
import com.ly.load.admin.DataStatisticsLoad;
import com.ly.util.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DataStatisticsServlet")
public class DataStatisticsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataStatisticsLoad dataStatisticsLoad = new DataStatisticsLoad(request, response);
        //月数据
        DataStatistics mDataStatistics;
        //季度数据
        DataStatistics dDataStatistics;
        //年度数据
        DataStatistics yDataStatistics;
        try {
            mDataStatistics = dataStatisticsLoad.queryMData();
            dDataStatistics = dataStatisticsLoad.queryMData();
            yDataStatistics = dataStatisticsLoad.queryYData();
            request.setAttribute("mDataStatistics", mDataStatistics);
            request.setAttribute("dDataStatistics", dDataStatistics);
            request.setAttribute("yDataStatistics", yDataStatistics);
            System.out.println("m " + mDataStatistics.getPrice() + "d " + dDataStatistics.getPrice() + "y " + yDataStatistics);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            System.out.println("查询销售数据出错");
            e.printStackTrace();
        }

    }
}
