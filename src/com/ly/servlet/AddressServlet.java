package com.ly.servlet;

import com.ly.bean.Adress;
import com.ly.bean.User;
import com.ly.dao.AddressDao;
import com.ly.load.AddrLoad;
import com.ly.util.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class AddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user;
        Adress adress;
        AddressDao addressDao;
        String name;
        String addr;
        String code;
        String phone;
        String field = "uid";
        HttpSession session = request.getSession();
        //获取参数
        user = (User) session.getAttribute("user");
        name = request.getParameter("name");
        addr = request.getParameter("addr");
        code = request.getParameter("code");
        phone = request.getParameter("phone");
        System.out.println("name:" + name + "\taddr: " + addr + "\t code: " + code + "\t phone : " + phone);
        if(name == null || "".equals(name) || addr == null || "".equals(addr) || code==null || "".equals(code) || phone == null || "".equals(phone)){
            //数据填写不完整
            response.getWriter().println("1");

        }else {

            if(phone.length() != 11 || ! Config.isMobile(phone)){
                //手机号格式不对
                response.getWriter().println("2");
            }else if(addr.length() > 50 || addr.length() < 5) {
                //地址长度不符
                response.getWriter().println("5");
            }else {
                    //修改资料
                    addressDao = new AddressDao();
                    adress = new Adress();
                    adress.setPhone(phone);
                    adress.setCode(code);
                    adress.setName(name);
                    adress.setAddr(addr);
                    try {
                        if(addressDao.update(adress, field, user.getId())){
                            //修改成功
                            session.setAttribute("adress", adress);
                            response.getWriter().println("3");
                        }else {
                            //修改失败
                            response.getWriter().println("4");
                        }
                    }catch (SQLException e){
                        System.out.println("[ERROR:] addressServlet 修改资料");
                        e.printStackTrace();
                    }

                }
            }



        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //加载地址信息
        Adress adress;
        HttpSession session = request.getSession();

        AddrLoad addrLoad = new AddrLoad(request, response);

        adress = addrLoad.queryAddr();

        session.setAttribute("adress", adress);

        //重定向
       //response.sendRedirect("user_center_site.jsp");
        request.getRequestDispatcher("user_center_site.jsp").forward(request, response);

    }
}
