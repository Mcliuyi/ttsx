<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>天天生鲜-用户中心</title>
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
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;用户中心</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>		
	</div>

	<div class="main_con clearfix">
		<div class="left_menu_con clearfix">
			<h3>用户中心</h3>
			<ul>
				<li><a href="user_center_info.jsp">· 个人信息</a></li>
				<li><a href="order" class="active">· 全部订单</a></li>
				<li><a href="address">· 收货地址</a></li>
			</ul>
		</div>
		<div class="right_content clearfix">
				<h3 class="common_title2">全部订单</h3>

			<c:forEach items="${orderArrayList}" var="order">
				<ul class="order_list_th w978 clearfix">
					<li class="col01">${order.createtime}</li>
					<li class="col02">订单号：${order.id}</li>
					<c:choose>
						<c:when test="${order.status == 1}">
							<li class="col02 stress">支付</li>
						</c:when>
						<c:otherwise>
							<li class="col02 stress">未支付</li>
						</c:otherwise>
					</c:choose>
				</ul>

					<table class="order_list_table w980">
						<tbody>
						<tr>
							<td width="55%">
					<c:forEach items="${order.commodityExtentArrayList}" var="commodityInfo">
								<ul class="order_goods_list clearfix">
									<li class="col01"><img src="${commodityInfo.commodity.img}"></li>
									<li class="col02">${commodityInfo.commodity.commodity_name}<em>${commodityInfo.commodity.price}元/${commodityInfo.commodity.unit}</em></li>
									<li class="col03">${commodityInfo.num}</li>
									<li class="col04">${commodityInfo.num * commodityInfo.commodity.price}元</li>
								</ul>
					</c:forEach>
							</td>
							<td width="15%">${order.price}元</td>
							<c:choose>
								<c:when test="${order.status == 1}">
									<td width="15%">已付款</td>
									<td width="15%"><a href="#" class="oper_btn">查看物流</a></td>
								</c:when>
								<c:otherwise>
									<td width="15%">待付款</td>
									<td width="15%"><a href="#" class="oper_btn">去付款</a></td>
								</c:otherwise>
							</c:choose>

						</tr>
						</tbody>
					</table>

			</c:forEach>




				<div class="pagenation">
					<c:if test="${page>1}">
						<a href="order?&page=${page - 1}" id="uppage">上一页</a>
					</c:if>
					<c:forEach begin="1" end="${o_page}" var="p">
						<c:choose>
							<c:when test="${page == p}">
								<a href="order?page=${p}" class="active">${p}</a>
							</c:when>
							<c:otherwise>
								<a href="order?page=${p}">${p}</a>
							</c:otherwise>
						</c:choose>

					</c:forEach>
					<c:if test="${page<o_page}">
						<a href="order?page=${page + 1}" id="onpage">下一页</a>
					</c:if>
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
	
</body>
</html>