package com.ly.load.admin;

import com.ly.bean.Commodity;
import com.ly.bean.CommodityType;
import com.ly.dao.CommodityDao;
import com.ly.dao.CommodityTypeDao;
import com.ly.load.BaseLoad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommodityInfoLoad extends BaseLoad {
    public CommodityInfoLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }


    /**
     * 查询所有商品类型
     * @return
     * @throws SQLException
     */
    public ArrayList<CommodityType> queryTye() throws SQLException {
        ArrayList<CommodityType> commodityTypeArrayList;

        CommodityTypeDao commodityTypeDao = new CommodityTypeDao();

        commodityTypeArrayList = commodityTypeDao.queryAll();

        commodityTypeDao.close();

        return commodityTypeArrayList;
    }

}
