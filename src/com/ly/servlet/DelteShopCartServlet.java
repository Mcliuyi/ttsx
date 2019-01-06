package com.ly.servlet;

import com.ly.bean.User;
import com.ly.dao.ShopCartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class DelteShopCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户对象
        User user;
        //商品id
        int cid;
        //获取session对象
        HttpSession session = request.getSession();

        ShopCartDao shopCartDao = new ShopCartDao();

        user = (User) session.getAttribute("user");

        cid = Integer.parseInt(request.getParameter("id"));

        try {
            //调用删除方法
            shopCartDao.del(user.getId(), cid);
            response.getWriter().print("1");
        } catch (SQLException e) {
            response.getWriter().print("2");
            e.printStackTrace();
        }finally {
            shopCartDao.close();
        }

        //转发请求
       // request.getRequestDispatcher("cart.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
