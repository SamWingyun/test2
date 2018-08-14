<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h4>最新加入的商品</h4>
	<c:forEach items="${car_list}" var="car" varStatus="index">
		<div class="one border">
			<img width="50px;" src="upload/${car.shp_tp}"/>
			<span class="one_name">
				${car.sku_mch}
			</span>
			<span class="one_prece">
				<b>${car.sku_jg}元</b><br />
				<b>${car.hj}元</b><br />
				<b>${car.tjshl}件</b><br />
			</span>
		</div>
	</c:forEach>
	
</body>
</html>