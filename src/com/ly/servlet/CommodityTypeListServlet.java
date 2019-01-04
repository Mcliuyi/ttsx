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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//@WebServlet(name = "CommodityTypeServlet")
public class CommodityTypeListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //商品数组
        ArrayList<Commodity> commodityArrayList;
        CommodityType commodityType;
        ArrayList<Commodity> newCommdityList;
        CommodityDao commodityDao = new CommodityDao();
        CommodityTypeDao commodityTypeDao = new CommodityTypeDao();
        HttpSession session = request.getSession();
        //用于记录价格的排序, 默认升序
        String isSort;
        int page;
        int num = 15;
        int total_page=0;
        int typeId =  Integer.parseInt(request.getParameter("id"));
        int sort = 0;
        String ruleField = null;


        //获取当前页面价格排序状态
        isSort = (String) session.getAttribute("isSort");
        if(isSort == null){
            isSort = "false";
        }
        //判断排序规则，升序还是降序
        //如果不包含page这个参数就进排序改变，否则不变
        if(request.getParameter("page") == null){
            if("true".equals(isSort)){
                sort = 2;
                isSort = "false";
            }else if("false".equals(isSort)){
                sort = 1;
                isSort = "true";
            }
        }else {
            if("true".equals(isSort)){
                sort = 2;
            }else if("false".equals(isSort)){
                sort = 1;
            }
        }

        //获取排序规则
        ruleField = request.getParameter("ruleField");
        if(ruleField == null){
            ruleField = (String)session.getAttribute("ruleField");
        }else if("default".equals(ruleField)){
            ruleField = null;
        }
        System.out.println("排序字段：" + ruleField);
        System.out.println("排序规则：" + isSort);

        //获取当前页数
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }else {
            page = 1;
        }

        try {

            //查询商品信息
            commodityArrayList = commodityDao.query(typeId, (page-1)*num, num, sort, ruleField);
            request.setAttribute("commodityList", commodityArrayList);
            //查询类型新消息
            commodityType = commodityTypeDao.query(typeId);
            request.setAttribute("commodityType", commodityType);
            //查询当前类型最新添加的两个商品
            newCommdityList = commodityDao.query(typeId,0,2, 2, "create_time");
            request.setAttribute("newCommdityList", newCommdityList);

            int total_num = commodityDao.query(commodityType.getId());
           // System.out.println("商品总数量：" + total_num + "num" + num );

            //查询某种类型商品数量
            total_page =  (int) Math.ceil( (float)total_num / num) ;

            request.setAttribute("pagenum", total_page);
            //存入当前页数
            request.setAttribute("page", page);
            //存入当前排序规则
            session.setAttribute("isSort", isSort);
            //存入当前排序字段
            session.setAttribute("ruleField", ruleField);

            request.getRequestDispatcher("list.jsp").forward(request,response);

        } catch (SQLException e) {
            System.out.println("CommodityTypeListServlet 查询数据出错");
            e.printStackTrace();
        }finally {
            commodityDao.close();
            commodityTypeDao.close();
        }


    }
}
