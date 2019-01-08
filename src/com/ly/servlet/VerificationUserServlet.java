package com.ly.servlet;

import com.ly.bean.User;
import com.ly.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VerificationUserServlet")
public class VerificationUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户名。判断用户名是否已存在
        String uname = request.getParameter("uname");

        UserDao userDao = new UserDao();
        User user;
        try {
            user = userDao.query(uname);
            if(userDao.exits(user)){
                //已存在
                response.getWriter().print("1");
            }else {
                response.getWriter().print("2");
            }
        } catch (SQLException e) {
            System.out.println("验证用户是否已存在时出错");
            response.getWriter().print("3");
            e.printStackTrace();
        }finally {
            userDao.close();
        }

    }
}
