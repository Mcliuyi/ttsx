package com.ly.servlet.admin;

import com.ly.bean.User;
import com.ly.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //用户名
        String uname = request.getParameter("user_name");
        String upwd = request.getParameter("user_pwd");
        Boolean rlt;
        User user;
        String error = null;
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        System.out.println("后台管理");
        //查询用户是否存在
        try {
            user = userDao.query(uname);
            if(user != null){
                rlt = userDao.verificationPwd(upwd, user);
                if(!rlt){
                    error = "密码错误";
                }else {
                    if(user.getIsAdmin() == 1){
                        //验证通过
                        session.setAttribute("auser", user);
                        //请求转发
                        //request.getRequestDispatcher("index.jsp").forward(request, response);
                        response.sendRedirect("index");
                    }else {
                        error = "不是管理员";
                    }

                }
            }else {
                error = "用户不存在";
            }
            if ( !(error == null || "".equals(error)) ){
                request.setAttribute("error", error);
                //请求转发
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            System.out.println("查询用户是否存在报错");
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
