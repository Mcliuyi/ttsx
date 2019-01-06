package com.ly.servlet;

import com.ly.bean.Commodity;
import com.ly.bean.ShopCart;
import com.ly.bean.User;
import com.ly.dao.CommodityDao;
import com.ly.dao.ShopCartDao;
import com.ly.load.ShopNumLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class AddShopCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Commodity commodity;
        CommodityDao commodityDao;
        ShopCartDao shopCartDao;
        User user;
        Boolean rlt;
        int c_num; //记录购物车商品数量

        HttpSession session  = request.getSession();

        //获取商品id
        int cid = Integer.parseInt(request.getParameter("id"));
        //获取用户信息
        user = (User) session.getAttribute("user");
        //获取商品数量
        int num = Integer.parseInt(request.getParameter("num"));
        //commodityDao = new CommodityDao();
        shopCartDao  = new ShopCartDao();
        //查询商品信息
        try {
            //commodity = commodityDao.query("id", String.valueOf(cid));
            //判断商品是否存在购物车
            rlt = shopCartDao.queryExist(user.getId(), cid);
            if(rlt){
                //存在购物车，则修改
                //1. 获取该商品在购物车中的数量
                c_num = shopCartDao.query(user.getId(), cid);
                //2。 修改
                num = c_num + num;
                shopCartDao.update(num, user.getId(), cid);
            }else {
                //将商品存入数据库
                shopCartDao.add(cid, num, user.getId());
            }
            //更新session中的购物车商品数量
            ShopNumLoad shopNumLoad = new ShopNumLoad(request, response);
            shopNumLoad.loadShopNum(user.getId());
            response.getWriter().print("1");
        } catch (SQLException e) {
            System.out.println("添加购物车查询失败");
            response.getWriter().print("2");
            e.printStackTrace();
        }finally {
            shopCartDao.close();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
