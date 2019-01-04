package com.ly.servlet;

import com.ly.bean.User;
import com.ly.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String rm_name = request.getParameter("rm_name");
        System.out.println("rm_name" +  rm_name);
        User user = null;
        UserDao userDao;
        HttpSession session;
        if(uname == null || upwd == null){
            //用户名或密码为空
            response.getWriter().println("1");
        }else {
            //验证用户是否存在
            userDao = new UserDao();
            try {
                user = userDao.query(uname);
            } catch (SQLException e) {
                System.out.println("[ERROR]: 登录时查询用户失败");
                userDao.close();
                e.printStackTrace();
            }

            if(!userDao.exits(user)){
                //用户不存在
                response.getWriter().println("2");
            }else {
                //验证通过

                session=request.getSession();
                session.setAttribute("user",user);
                if("true".equals(rm_name)){
                    System.out.println("设置cookies");
                    //设置cookies
                    Cookie userName = new Cookie("user", uname);
                    Cookie uid = new Cookie("uid", String.valueOf(user.getId()));
                    userName.setMaxAge(60*60*24);
                    uid.setMaxAge(60*60*24);
                    userName.setDomain("localhost");
                    uid.setDomain("localhost");
                    response.addCookie(userName);
                    response.addCookie(uid);
                }
                response.getWriter().println("3");
               // response.sendRedirect("commodityType");
                try {
                    userDao.setEndTime(user.getId());
                } catch (SQLException e) {
                    System.out.println("设置用户最后登录时间出错");
                    e.printStackTrace();
                }finally {
                    userDao.close();
                }


            }

        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
