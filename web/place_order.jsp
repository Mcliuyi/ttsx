<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>天天生鲜-提交订单</title>
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
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
					<a href="order">我的订单</a>
				</div>
			</div>
		</div>		
	</div>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;提交订单</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>		
	</div>
	
	<h3 class="common_title">确认收货地址</h3>

	<div class="common_list_con clearfix">
		<dl>
			<dt>寄送到：</dt>
			<dd><input type="radio" name="" checked="">${adress.addr} （${adress.name}  收） ${adress.phone}</dd>
		</dl>
		<a href="address" class="edit_site">编辑收货地址</a>

	</div>
	
	<h3 class="common_title">支付方式</h3>	
	<div class="common_list_con clearfix">
		<div class="pay_style_con clearfix">
			<input type="radio" name="pay_style" checked>
			<label class="cash">货到付款</label>
			<input type="radio" name="pay_style">
			<label class="weixin">微信支付</label>
			<input type="radio" name="pay_style">
			<label class="zhifubao"></label>
			<input type="radio" name="pay_style">
			<label class="bank">银行卡支付</label>
		</div>
	</div>

	<h3 class="common_title">商品列表</h3>
	
	<div class="common_list_con clearfix">
		<ul class="goods_list_th clearfix">
			<li class="col01">商品名称</li>
			<li class="col02">商品单位</li>
			<li class="col03">商品价格</li>
			<li class="col04">数量</li>
			<li class="col05">小计</li>		
		</ul>

		<c:forEach items="${commodityExtentArrayList}" var="commodityExtent" varStatus="status">
			<ul class="goods_list_td clearfix" name="${commodityExtent.commodity.id}">
				<li class="col01">${status.count}</li>
				<li class="col02"><img src="${commodityExtent.commodity.img}"></li>
				<li class="col03">${commodityExtent.commodity.commodity_name}</li>
				<li class="col04">${commodityExtent.commodity.unit}</li>
				<li class="col05">${commodityExtent.commodity.price}元</li>
				<li class="col06">${commodityExtent.num}</li>
				<li class="col07">${commodityExtent.commodity.price * commodityExtent.num}元</li>
			</ul>
		</c:forEach>


	</div>

	<h3 class="common_title">总金额结算</h3>

	<div class="common_list_con clearfix">
		<div class="settle_con">
			<div class="total_goods_count">共<em>${orderInfo.num}</em>件商品，总金额<b>${orderInfo.price}元</b></div>
			<div class="transit">运费：<b>0元</b></div>
			<div class="total_pay">实付款：<b>${orderInfo.price}元</b></div>
		</div>
	</div>

	<div class="order_submit clearfix">
		<a href="scs" id="order_btn">提交订单</a>
	</div>	

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

	<div class="popup_con">
		<div class="popup">
			<p>订单提交成功！</p>
		</div>
		
		<div class="mask"></div>
	</div>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/place_order.js"></script>
</body>
</html>