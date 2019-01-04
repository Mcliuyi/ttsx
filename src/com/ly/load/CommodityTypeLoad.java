package com.ly.load;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityType;
import com.ly.dao.CommodityDao;
import com.ly.dao.CommodityTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommodityTypeLoad {

    /**
     * 在加载主页前，存入商品类型和商品数据
     * @param request
     * @param response
     */
    public static void getGoods(HttpServletRequest request, HttpServletResponse response){

        ArrayList<CommodityType> commodityTypeArrayList;
        CommodityTypeDao commodityTypeDao = new CommodityTypeDao();
        HttpSession session = request.getSession();
        CommodityDao commodityDao = new CommodityDao();
        //商品数组
        ArrayList<Commodity> commodityArrayList;
        int page = 0;
        int num = 4;

        try {
            commodityTypeArrayList = commodityTypeDao.queryAll();
            //将类型对象的商品添加到对应的类型对象中
            for (CommodityType comm:commodityTypeArrayList) {

                commodityArrayList = commodityDao.query(String.valueOf(comm.getId()), page, num, 0, null, 0, null);
                comm.setCommodityArrayList(commodityArrayList);

            }
            session.setAttribute("typeList", commodityTypeArrayList);
            //request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
