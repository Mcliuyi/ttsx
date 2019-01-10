package com.ly.load.admin;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityType;
import com.ly.dao.CommodityDao;
import com.ly.dao.CommodityTypeDao;
import com.ly.load.BaseLoad;
import com.ly.util.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddCommodityLoad extends BaseLoad {
    public AddCommodityLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     *添加商品
     */
    public Boolean add(){
        Boolean rlt = false;
        Commodity commodity = new Commodity();
        String name = request.getParameter("user_name");
        String commodity_info = request.getParameter("commodity_info");
        int tid =  Integer.parseInt(request.getParameter("tid"));
        double price = Double.parseDouble(request.getParameter("price"));
        String unit = request.getParameter("unit");
        String img = request.getParameter("image");
        String introduction = request.getParameter("introduction");

        System.out.println("img :" + img);

        commodity.setCreateTime(Config.getTime());
        commodity.setIntroduction(introduction);
        commodity.setUnit(unit);
        commodity.setPrice(price);
        commodity.setTid(tid);
        commodity.setImg(img);
        commodity.setCommodity_name(name);
        commodity.setCommodity_info(commodity_info);

        //添加数据
        CommodityDao commodityDao = new CommodityDao();
        try {
            rlt = commodityDao.add(commodity);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            commodityDao.close();
        }
        return rlt;
    }

    /**
     * 查询所有商品类型数据
     * @return
     * @throws SQLException
     */
    public ArrayList<CommodityType> queryType() throws SQLException {

        ArrayList<CommodityType> commodityTypeArrayList;

        CommodityTypeDao commodityTypeDao = new CommodityTypeDao();

        //查询所有商品类型数据
        commodityTypeArrayList = commodityTypeDao.queryAll();

        return commodityTypeArrayList;
    }

}
