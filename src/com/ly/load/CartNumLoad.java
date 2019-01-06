package com.ly.load;

import com.ly.bean.ShopCart;
import com.ly.bean.User;
import com.ly.dao.ShopCartDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CartNumLoad extends BaseLoad {

    public CartNumLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * 修改购物车的商品数量
     * @return true or false
     * @throws SQLException
     */
    public Boolean updateCommodityNum() throws SQLException {
        Boolean rlt;
        //修改购物车商品数量
        User user;
        ShopCartDao shopCartDao = new ShopCartDao();
        HttpSession session = request.getSession();
        //获取商品id
        int cid = Integer.parseInt(request.getParameter("id"));
        //获取用户对象
        user = (User) session.getAttribute("user");
        //获取修改数量
        int num = Integer.parseInt(request.getParameter("num"));
        //修改购物车的数据
        rlt = shopCartDao.update(num, user.getId(), cid);
        System.out.println("修改购物车数量结果：" + rlt);
        shopCartDao.close();
        return rlt;

    }


}
