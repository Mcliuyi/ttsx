package com.ly.servlet;

import com.ly.bean.User;
import com.ly.dao.UserDao;
import com.ly.util.Config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String ucpwd = request.getParameter("cpwd");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        User user;
        UserDao userDao;
        //String pattern = "^[a-z0-9][\\w\\.\\-]*@[a-z0-9\\-]+(\\.[a-z]{2,5}){1,2}$";
        boolean isRlt;

        if(uname == null || ucpwd == null || upwd == null || email == null || phone == null){
            //传递数据
            response.getWriter().println("1");
        }else if(uname.length() > 20 || uname.length() < 5){
            //传递数据 账号长度不符
            response.getWriter().println("2");
        }else if(! ucpwd.equals(upwd)){
            //传递数据 两次密码不同
            response.getWriter().println("3");
        }else {
            isRlt = Pattern.matches(Config.REGEX_EMAIL, email);
            if(!isRlt){
                //邮箱格式不对
                response.getWriter().println("4");
            }else {
                //验证用户名是否已存在
                userDao = new UserDao();
                try {
                    user = userDao.query(uname);
                    if(userDao.exits(user)){
                        //用户名已存在
                        response.getWriter().println("5");
                    }else {

                        // 验证通过添加用户
                        user = new User();
                        user.setEamil(email);
                        user.setUname(uname);
                        user.setUpwd(upwd);
                        user.setCreateTime();
                        try {
                            isRlt = userDao.add(user);
                            //添加用户失败
                            if(!isRlt){
                                //添加用户失败
                                response.getWriter().println("6");
                            }else {
                                //添加成功
                                response.getWriter().println("7");
                            }
                        } catch (SQLException e) {
                            System.out.println("[ERROR]: 添加用户时异常");
                            e.printStackTrace();
                        }finally {
                            userDao.close();
                        }

                    }
                } catch (SQLException e) {
                    System.out.println("[ERROR]: 注册时查询用户名是否已存在报错");
                    userDao.close();
                    e.printStackTrace();
                }


            }
        }





    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
