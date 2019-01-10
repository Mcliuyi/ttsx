package com.ly.servlet;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityType;
import com.ly.dao.CommodityDao;
import com.ly.dao.CommodityTypeDao;
import com.ly.load.admin.CommodityInfoLoad;

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
        String typeId = request.getParameter("id") ;
        int sort = 0;
        String ruleField = null;
        //判断是否有请求分类
        if( typeId == null){
            typeId =  "0";
        }


        //是否为模糊查询
        int blurry;
        //获取需要模糊内容
        String blurryContent = request.getParameter("blurry");
        String blurrFiedl = null;
        System.out.println("模糊查询内容：" + blurryContent);
        if(blurryContent == null || "".equals(blurryContent)){
            blurry = 0;
            blurryContent = null;
        }else {
            blurry = 1;
            //如果是模糊查询则将typId切换为字段
            blurrFiedl = "%" + blurryContent + "%";
        }

        //获取当前页面价格排序状态
        isSort = (String) session.getAttribute("isSort");
        if(isSort == null){
            isSort = "false";
        }
        //判断排序规则，升序还是降序
        //如果不包含page这个参数就进排序改变，否则不变
        if(request.getParameter("page") == null ){
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
            //System.out.println("typeid -----" + typeId);
            //查询商品信息
            commodityArrayList = commodityDao.query(typeId, (page-1)*num, num, sort, ruleField, blurry, blurrFiedl);
            request.setAttribute("commodityList", commodityArrayList);
            //查询类型新消息
            System.out.println("类型id"+ typeId);
            commodityType = commodityTypeDao.query(Integer.parseInt(typeId));
            request.setAttribute("commodityType", commodityType);
            //查询当前类型最新添加的两个商品
            newCommdityList = commodityDao.query(typeId,0,2, 2, "create_time", 0, null);
            request.setAttribute("newCommdityList", newCommdityList);

            int total_num;
            if(blurryContent == null){
                total_num  = commodityDao.query(commodityType.getId());
            }else {
                total_num  = commodityDao.query(blurrFiedl);
            }

            System.out.println("商品总数量：" + total_num + "num" + num );

            //查询某种类型商品数量
            total_page =  (int) Math.ceil( (float)total_num / num) ;
            System.out.println("total_page:" + total_page);
            request.setAttribute("pagenum", total_page);
            //存入当前页数
            request.setAttribute("page", page);
            //存入当前排序规则
            session.setAttribute("isSort", isSort);
            //存入当前排序字段
            session.setAttribute("ruleField", ruleField);
            //存入模糊查询记录，方便下一页，排序
            session.setAttribute("blurryContent", blurryContent);
            //存入当前类型
            request.setAttribute("type_id", typeId);
            //查询所有商品类型
            ArrayList<CommodityType> commodityTypeArrayList;
            CommodityInfoLoad commodityInfoLoad = new CommodityInfoLoad(request, response);
            commodityTypeArrayList = commodityInfoLoad.queryTye();
            request.setAttribute("commodityTypeArrayList", commodityTypeArrayList);

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
