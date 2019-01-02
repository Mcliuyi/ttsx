package com.ly.fllter;

import com.ly.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class VerifyLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        User user;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        user = (User)session.getAttribute("user");
        if(user != null){
            chain.doFilter(req, resp);
        }else {
            response.sendRedirect("login.jsp");
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
