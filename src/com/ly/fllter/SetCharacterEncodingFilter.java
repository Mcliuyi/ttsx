package com.ly.fllter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


public class SetCharacterEncodingFilter implements Filter {

    String encoding;

    public void destroy() {
        this.encoding = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(this.encoding == null)
            this.encoding = "UTF-8";
            //设置请求编码
        req.setCharacterEncoding(this.encoding);
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
        //获取在xml设置的参数
        this.encoding = config.getInitParameter("encoding");
    }

}
