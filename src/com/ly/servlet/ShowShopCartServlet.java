package com.ly.servlet;

import com.ly.bean.CommodityExtent;
import com.ly.bean.ShopCart;
import com.ly.bean.User;
import com.ly.dao.CommodityDao;
import com.ly.load.CartNumLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class ShowShopCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //修改商品数量
        CartNumLoad cartNumLoad = new CartNumLoad(request, response);
        Boolean rlt;
        try {
            rlt = cartNumLoad.updateCommodityNum();
            if(rlt){
                response.getWriter().print("1");
            }else {
                response.getWriter().print("2");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //展示购物车商品
        User user;
        HttpSession session = request.getSession();
        CommodityDao commodityDao = new CommodityDao();
        ArrayList<CommodityExtent> commodityExtentArrayList;
        ShopCart shopCart = new ShopCart();
        //购物车总价格
        double t_price = 0;
        int num = 0;
        //1. 获取用户对象
        user = (User)session.getAttribute("user");

        //2. 查询用户购物车信息
        try {
            commodityExtentArrayList = commodityDao.queryShopCart(user.getId());

            //获取购物车的总价值
            for (CommodityExtent commodityExtent: commodityExtentArrayList) {
                t_price += commodityExtent.getNum() * commodityExtent.getCommodity().getPrice();
            }
            shopCart.setNum(num);
            shopCart.setT_price(t_price);
            System.out.println(commodityExtentArrayList.size());
            System.out.println("数量： " + shopCart.getNum() + "价格：" + shopCart.getT_price());
            //3. 将数据保存
            request.setAttribute("commodityExtentArrayList", commodityExtentArrayList);
            request.setAttribute("shopCart", shopCart);
            //跳转到购物车页面
            //response.sendRedirect("cart.jsp");
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
