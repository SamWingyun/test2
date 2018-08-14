<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加成功</title>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="css/css.css"/>
<link rel="stylesheet" type="text/css" href="css/finishCart.css"/>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
   <script type="text/javascript">
   </script>
</head>
<body>
        <div class="top">
			<div class="top_text">
				<a href="">供应商注册</a>
				<a href="">供应商登录</a>
				<a href="">${user.yh_nch }</a>
			    <c:if test="${user==null}">
			        <a href="goto_login.do" target="_blank">用户登录</a>
		        </c:if>
				<c:if test="${user!=null}">
			        <a href="logout.do" >用户注销</a>
		        </c:if>
			</div>
	    </div>
	
		<div class="search">
			<div class="logo"><img src="images/jingdong-logo.png" alt=""></div>
			<div class="search_on">
				<div class="se">
					<input type="text" name="search" class="lf">
					<input type="submit" class="clik" value="搜索">
				</div>
			</div>
			
			<!-- 引入mini购物车 -->
	        <jsp:include page="sale_miniCar.jsp"></jsp:include> 
		</div>
		
		
		<div class="cartPro">
			<span class="dec">该商品已加入购物车</span>
			<div class="lec">
				<a href=""><img style="height:60px;width:80px" src="upload/${car.shp_tp }"/></a>
			</div>
			<span class="lec_name">
				${car.sku_mch }
			</span>
			<div class="shop_des">
				<a href="sale_search_goto_sku_detail/${car.sku_id}/${car.shp_id}.do"><img src="images/shop_des.png"/></a>
			</div>
			<div class="shop_cart">
				<a href="goto_shoppingCar.do"><img src="images/shop_cart.png"/></a>
			</div>
		</div>
		
		
		
	</body>
</html>
