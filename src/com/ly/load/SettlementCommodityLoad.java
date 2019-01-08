package com.ly.load;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ly.bean.*;
import com.ly.dao.CommodityDao;
import com.ly.dao.OrderDao;
import com.ly.dao.OrderDetailDao;
import com.ly.dao.ShopCartDao;
import com.ly.util.Config;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;

public class SettlementCommodityLoad extends BaseLoad {
    public SettlementCommodityLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }


    /**
     * 获取请求数据
     * @return placeOrderArrayList 存放PlaceOrder对象的数组
     */
    public ArrayList<PlaceOrder> settlementOrder(){

        Gson gson = new Gson();
        //接受数据
        String data;
        //存放对象数组
        ArrayList<PlaceOrder> placeOrderArrayList;

        data = request.getParameter("goodsArr");

        Type type = new TypeToken<ArrayList<PlaceOrder>>() {}.getType();
        //获取到对象数组
        placeOrderArrayList = gson.fromJson(data, type);

        return placeOrderArrayList;
    }

    /**
     * 获取订单内的商品信息
     * @param placeOrderArrayList 存放PlaceOrder对象的数组
     * @return commodityExtentArrayList 存放商品扩展类的数组
     */
    public ArrayList<CommodityExtent> queryCommodityInfo(ArrayList<PlaceOrder> placeOrderArrayList){

        CommodityDao commodityDao = new CommodityDao();
        //存放需要结算的商品数据
        ArrayList<CommodityExtent> commodityExtentArrayList = new ArrayList<CommodityExtent>();
        CommodityExtent commodityExtent;
        for (PlaceOrder placeOrder: placeOrderArrayList) {

            try {
                //查询商品信息
                commodityExtent = commodityDao.queryExtent("id", String.valueOf(placeOrder.getId()));
                commodityExtent.setNum(placeOrder.getNum());
                //添加到列表
                commodityExtentArrayList.add(commodityExtent);
            } catch (SQLException e) {
                System.out.println("queryCommodityInfo 查询商品信息错误");
                e.printStackTrace();
            }
        }
        commodityDao.close();
        return commodityExtentArrayList;
    }

    /**
     * 设置订单对象信息
     * @param commodityExtentArrayList 存放商品扩展类的数组
     * @return orderInfo 订单信息对象
     */
    public OrderInfo setOrderInfo(ArrayList<CommodityExtent> commodityExtentArrayList){

        OrderInfo orderInfo = new OrderInfo();
        int num = 0;
        double price=0.0;
        for(CommodityExtent commodityExtent:  commodityExtentArrayList) {
            num += commodityExtent.getNum();
            price += commodityExtent.getNum() * commodityExtent.getCommodity().getPrice();
        }
        orderInfo.setNum(num);
        orderInfo.setPrice(price);

        return orderInfo;
    }

    /**
     * 删除提交订单的购物车商品
     * @param commodityExtentArrayList
     * @param uid
     */
    public void delShopCart(ArrayList<CommodityExtent> commodityExtentArrayList, int uid) throws SQLException {

        int cid;
        ShopCartDao shopCartDao = new ShopCartDao();
        //循环取出商品id并删除
        for(CommodityExtent commodityExtent:  commodityExtentArrayList) {
            cid = commodityExtent.getCommodity().getId();
            shopCartDao.del(uid, cid);
        }

        shopCartDao.close();
    }


    public Order createOrder(int uid, double price) throws SQLException {

        //创建订单对象
        Order order = new Order();
        order.setId(Integer.parseInt(Config.getOrderid()));
        order.setUid(uid);
        order.setCreatetime(Config.getTime());
        order.setPrice(price);

        //存入数据库
        OrderDao orderDao = new OrderDao();

        orderDao.add(order);

        orderDao.close();
        return order;
    }

    /**
     * 创建订单详细
     * @param commodityExtentArrayList
     * @param oid
     */
    public void createOrderDetail(ArrayList<CommodityExtent> commodityExtentArrayList, int oid) throws SQLException {
        OrderDetail orderDetail;
        OrderDetailDao orderDetailDao = new OrderDetailDao();
        for(CommodityExtent commodityExtent:  commodityExtentArrayList) {
            orderDetail = new OrderDetail();
            orderDetail.setId(oid);
            orderDetail.setCid(commodityExtent.getCommodity().getId());
            orderDetail.setNum(commodityExtent.getNum());
            orderDetailDao.add(orderDetail);
        }

        orderDetailDao.close();
    }



}
