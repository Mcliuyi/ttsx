<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>天天生鲜-用户中心</title>
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/site.js"></script>
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
					<a href="address">我的订单</a>
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
				<li><a href="order">· 全部订单</a></li>
				<li><a href="address" class="active">· 收货地址</a></li>
			</ul>
		</div>
		<div class="right_content clearfix">
				<h3 class="common_title2">收货地址</h3>
				<div class="site_con">
					<dl>
						<dt>当前地址：</dt>
						<c:choose>
							<C:when test="${adress == null}">
								<dd>暂无地址</dd>
							</C:when>
							<c:otherwise>
								<dd>${adress.addr} （${adress.name}  收） ${adress.phone} </dd>

							</c:otherwise>

						</c:choose>

					</dl>					
				</div>
				<h3 class="common_title2">编辑地址</h3>
				<div class="site_con">
					<form  id="addr_form">
						<div class="form_group">
							<label>收件人：</label>
							<input type="text" name="name" id="name" value="${adress.name}">
						</div>
						<div class="form_group form_group2">
							<label>详细地址：</label>
							<textarea class="site_area" name="addr" id="addr">${adress.addr}</textarea>
						</div>
						<div class="form_group">
							<label>邮编：</label>
							<input type="text" name="code" id="code" value="${adress.code}">
						</div>
						<div class="form_group">
							<label>手机：</label>
							<input type="text" name="phone" id="phone" value="${adress.phone}">
						</div>
						<input type="submit" name="" value="提交" class="info_submit">
					</form>
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