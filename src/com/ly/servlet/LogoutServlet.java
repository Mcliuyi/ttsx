package com.ly.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        //移除用户信息
        session.removeAttribute("user");
        Cookie[] allCookies = request.getCookies();
        if(allCookies != null){
            for(int i =0; i < allCookies.length ; i++){
                //取出
                Cookie temp = allCookies[i];
                System.out.println("cookies: " + temp.getName());
                if("user".equals(temp.getName())){
                    //设置有效时间为0
                    temp.setMaxAge(0);
                }else if("uid".equals(temp.getName())){
                    //设置有效时间为0
                    temp.setMaxAge(0);
                }
            }
        }

        response.sendRedirect("login.jsp");

    }
}
