package com.ly.load;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseLoad {

    public HttpServletRequest request;
    public HttpServletResponse response;

    public BaseLoad(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

}
