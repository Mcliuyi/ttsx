package com.ly.load;

import com.ly.dao.ShopCartDao;
import com.ly.load.BaseLoad;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ShopNumLoad extends BaseLoad{


    public ShopNumLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void loadShopNum(int uid) throws ServletException, IOException {
        //加载用户购物车信息
        int num;

        HttpSession session = request.getSession();
        ShopCartDao shopCartDao = new ShopCartDao();

        try {
            num = shopCartDao.query(uid);
            //添加购物车数量到session
            session.setAttribute("shopNum", num);
        } catch (SQLException e) {
            System.out.println("shopNumLoad查询购物车数量出错");
            e.printStackTrace();
        }


    }

}
