<%--
  Created by IntelliJ IDEA.
  User: liuyi
  Date: 2018-12-30
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>天天生鲜-购物车</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
<div class="header_con">
    <div class="header">
        <div class="welcome fl">欢迎来到天天生鲜!</div>
        <div class="fr">
            <c:choose>
                <c:when test="${user != null}">
                    <div class="login_info fl">
                        欢迎您：<em>${user.uname}</em>
                        <span>|</span>
                        <span class="user_link"><a href="logout">注销</a></span>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="login_btn fl">
                        <a href="login.jsp">登录</a>
                        <span>|</span>
                        <a href="register.jsp">注册</a>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="user_link fl">
                <span>|</span>
                <a href="user_center_info.jsp">用户中心</a>
                <span>|</span>
                <a href="cart">我的购物车</a>
                <span>|</span>
                <a href="user_center_order.jsp">我的订单</a>
            </div>
        </div>
    </div>
</div>

<div class="search_bar clearfix">
    <a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
    <div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
    <div class="search_con fr">
        <form action="list" method="get">
            <input type="text" class="input_text fl" name="blurry" placeholder="搜索商品">
            <input type="submit" class="input_btn fr" name="" value="搜索">
        </form>
    </div>
</div>

<div class="total_count">全部商品<em>${shopNum}</em>件</div>
<ul class="cart_list_th clearfix">
    <li class="col01">商品名称</li>
    <li class="col02">商品单位</li>
    <li class="col03">商品价格</li>
    <li class="col04">数量</li>
    <li class="col05">小计</li>
    <li class="col06">操作</li>
</ul>
<c:forEach items="${requestScope.commodityExtentArrayList}" var="commodityExtent">


    <ul class="cart_list_td clearfix" id="${commodityExtent.commodity.id}">
        <li class="col01"><input type="checkbox" class="checked" name="checkbox" checked></li>
        <li class="col02"><img src="${commodityExtent.commodity.img}"></li>
        <li class="col03">${commodityExtent.commodity.commodity_name}<br><em>${commodityExtent.commodity.price}元/${commodityExtent.commodity.unit}</em></li>
        <li class="col04">${commodityExtent.commodity.unit}</li>
        <li class="col05" name="sprice" >${commodityExtent.commodity.price}元</li>
        <li class="col06">
            <div class="num_add">
                <a href="javascript:;" class="add fl">+</a>
                <input type="text" class="num_show fl" value="${commodityExtent.num}">
                <a href="javascript:;" class="minus fl">-</a>
            </div>
        </li>
        <li class="col07" name="t_sprice" >${commodityExtent.commodity.price * commodityExtent.num}元</li>
        <li class="col08"><a href="#" name="del">删除</a></li>
    </ul>


</c:forEach>

<ul class="settlements">
    <li class="col01"><input type="checkbox" name="allcheck" checked=""></li>
    <li class="col02">全选</li>
    <li class="col03">合计(不含运费)：<span>¥</span><em class="price">${requestScope.shopCart.t_price}</em><br>共计<b class="snum">${shopNum}</b>件商品</li>
    <li class="col04"><a href="place_order.jsp">去结算</a></li>
</ul>

<div class="footer">
    <div class="foot_link">
        <a href="#">关于我们</a>
        <span>|</span>
        <a href="#">联系我们</a>
        <span>|</span>
        <a href="#">招聘人才</a>
        <span>|</span>
        <a href="#">友情链接</a>
    </div>
    <p>CopyRight © 2016 北京天天生鲜信息技术有限公司 All Rights Reserved</p>
    <p>电话：010-****888    京ICP备*******8号</p>
</div>

</body>
</html>