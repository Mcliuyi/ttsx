<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>天天生鲜-商品详情</title>
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
					<a href="order">我的订单</a>
				</div>
			</div>
		</div>		
	</div>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
		<div class="search_con fl">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
		<div class="guest_cart fr">
			<a href="cart" class="cart_name fl">我的购物车</a>
			<c:choose>
				<c:when test="${shopNum != null}">
					<div class="goods_count fl" id="show_count">${shopNum}</div>
				</c:when>
				<c:otherwise>
					<div class="goods_count fl" id="show_count">0</div>
				</c:otherwise>

			</c:choose>

		</div>
	</div>

	<div class="navbar_con">
		<div class="navbar clearfix">
			<div class="subnav_con fl">
				<h1>全部商品分类</h1>	
				<span></span>			
				<ul class="subnav">
					<c:forEach items="${sessionScope.typeList}" var="typeName">
						<li><a href="#model0${typeName.id}" class="${typeName.className}">${typeName.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<ul class="navlist fl">
				<li><a href="">首页</a></li>
				<li class="interval">|</li>
				<li><a href="">手机生鲜</a></li>
				<li class="interval">|</li>
				<li><a href="">抽奖</a></li>
			</ul>
		</div>
	</div>

	<div class="breadcrumb">
		<a href="#">全部分类</a>
		<span>></span>
		<a href="list?id=${commodityType.id}">${commodityType.name}</a>
		<span>></span>
		<a href="#">商品详情</a>
	</div>

	<div class="goods_detail_con clearfix">
		<div class="goods_detail_pic fl"><img src="${commodity.img}"></div>

		<div class="goods_detail_list fr">
			<h3>${commodity.commodity_name}</h3>
			<p>${commodity.commodity_info}</p>
			<div class="prize_bar">
				<span class="show_pirze">¥<em class="unit_price">${commodity.price}</em></span>
				<span class="show_unit">单  位：${commodity.unit}</span>
			</div>
			<div class="goods_num clearfix">
				<div class="num_name fl">数 量：</div>
				<div class="num_add fl">
					<a href="javascript:;" class="add fr">+</a>
					<input type="text" class="num_show fl" name="${commodity.id}" value="1">
					<a href="javascript:;" class="minus fr">-</a>	
				</div> 
			</div>
			<div class="total">总价：<em class="total">${commodity.price}元</em></div>
			<div class="operate_btn">
				<a href="javascript:;" class="buy_btn">立即购买</a>
				<a href="javascript:;" class="add_cart" id="add_cart" name="${commodity.id}">加入购物车</a>
			</div>
		</div>
	</div>

	<div class="main_wrap clearfix">
		<div class="l_wrap fl clearfix">
			<div class="new_goods">
				<h3>新品推荐</h3>
				<ul>
					<li>
						<a href="#"><img src="images/goods/goods001.jpg"></a>
						<h4><a href="#">进口柠檬</a></h4>
						<div class="prize">￥3.90</div>
					</li>
					<li>
						<a href="#"><img src="images/goods/goods002.jpg"></a>
						<h4><a href="#">玫瑰香葡萄</a></h4>
						<div class="prize">￥16.80</div>
					</li>
				</ul>
			</div>
		</div>

		<div class="r_wrap fr clearfix">
			<ul class="detail_tab clearfix">
				<li class="active">商品介绍</li>
				<li>评论</li>
			</ul>

			<div class="tab_content">
				<dl>
					<dt>商品详情：</dt>
					<dd>${commodity.introduction}</dd>
				</dl>
			</div>

		</div>
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
	<div class="add_jump"></div>

	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/addcart.js"></script>
	
</body>
</html>