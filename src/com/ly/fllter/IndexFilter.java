package com.ly.fllter;

import com.ly.load.CommodityTypeLoad;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "IndexFilter")
public class IndexFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println(request.getRequestURI());
        CommodityTypeLoad.getGoods(request, response);
        System.out.println("IndexFilter过滤器");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
            System.out.println("index过滤器");
    }

}
