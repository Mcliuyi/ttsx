<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>天天生鲜-商品列表</title>
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
					<a href="user_center_order.jsp">我的订单</a>
				</div>
			</div>
		</div>		
	</div>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
		<div class="search_con fl">
			<form action="list" method="get">
				<input type="text" class="input_text fl" name="blurry" placeholder="搜索商品">
				<input type="submit" class="input_btn fr" name="" value="搜索">
			</form>
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
						<li><a href="list?id=${typeName.id}" class="${typeName.className}">${typeName.name}</a></li>
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
		<a href="list?id=${commodityType.id}">${requestScope.commodityType.name}</a>
	</div>

	<div class="main_wrap clearfix">
		<div class="l_wrap fl clearfix">
			<div class="new_goods">
				<h3>新品推荐</h3>
				<ul>
					<c:forEach items="${newCommdityList}" var="commdity">
						<li>
							<a href="#"><img src="${commdity.img}"></a>
							<h4><a href="#">${commdity.commodity_name}</a></h4>
							<div class="prize">￥${commdity.price}</div>
						</li>
					</c:forEach>


				</ul>
			</div>
		</div>

		<div class="r_wrap fr clearfix">
			<div class="sort_bar">
                <c:choose>
                    <c:when test="${sessionScope.ruleField == null}">
                        <a href="#" class="active">默认</a>
                    </c:when>
                    <c:otherwise>
                        <a href="list?id=${commodityType.id}&ruleField=default&blurry=${blurryContent}">默认</a>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${sessionScope.ruleField == 'price'}">
                        <a href="list?id=${commodityType.id}&ruleField=price&blurry=${blurryContent}" class="active">价格</a>
                    </c:when>
                    <c:otherwise>
                        <a href="list?id=${commodityType.id}&ruleField=price&blurry=${blurryContent}" >价格</a>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${sessionScope.ruleField == 'click_num'}">
                        <a href="list?id=${commodityType.id}&ruleField=click_num&blurry=${blurryContent}" class="active" >人气</a>
                    </c:when>
                    <c:otherwise>
                        <a href="list?id=${commodityType.id}&ruleField=click_num&blurry=${blurryContent}" >人气</a>
                    </c:otherwise>
                </c:choose>
			</div>

			<ul class="goods_type_list clearfix">
				<c:forEach items="${commodityList}" var="commodity">
					<li>
						<a href="detail?id=${commodity.id}"><img src="${commodity.img}"></a>
						<h4><a href="detail?id=${commodity.id}">${commodity.commodity_name}</a></h4>
						<div class="operate">
							<span class="prize">${commodity.click_num}￥${commodity.price}</span>
							<span class="unit">${commodity.price}/${commodity.unit}</span>
							<a href="#" class="add_goods" title="加入购物车"></a>
						</div>
					</li>
				</c:forEach>


			</ul>

			<div class="pagenation">
				<c:if test="${page>1}">
					<a href="list?id=${commodityType.id}&page=${page - 1}&blurry=${blurryContent}" id="uppage">上一页</a>
				</c:if>
				<c:forEach begin="1" end="${pagenum}" var="p">
					<c:choose>
						<c:when test="${page == p}">
							<a href="list?id=${commodityType.id}&page=${p}&blurry=${blurryContent}" class="active">${p}</a>
						</c:when>
						<c:otherwise>
							<a href="list?id=${commodityType.id}&page=${p}&blurry=${blurryContent}">${p}</a>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				<c:if test="${page<pagenum}">
					<a href="list?id=${commodityType.id}&page=${page + 1}&blurry=${blurryContent}" id="onpage">下一页</a>
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