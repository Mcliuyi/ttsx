package com.ly.servlet.admin;

import com.ly.bean.User;
import com.ly.load.admin.UserManageLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UserManageServlet")
public class UserManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<User> userArrayList;

        UserManageLoad userManageLoad = new UserManageLoad(request, response);


        try {
            //获取到所有用户信息
            userArrayList = userManageLoad.getUser();
            request.setAttribute("userArrayList", userArrayList);
            //转发请求
            request.getRequestDispatcher("userInfo.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
