<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" href="css/style.css">
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<script type="text/javascript" src='js/jquery-1.7.2.min.js'></script>
<script type="text/javascript">
     
</script>
</head>
<body>
      <div class="Cbox">
		<table class="table table-striped table-bordered table-hover" >
		   <thead>
		     <tr>
		       <th></th>
		       <th>商品图片</th>
		       <th>商品名称</th>
		       <th>商品价格</th>
		       <th>商品合计</th>
		       <th>商品数量</th>
		       <th>发货地址</th>
		       <th>操作</th>
		     </tr>
		   </thead>
		   <tbody>
		       <c:forEach items="${list_car }" var="car" >
		         <tr>
		           <td><input ${car.shfxz==1?"checked":""} onChange="sale_shoppingCar_change_item(${car.id},${car.sku_id},this.checked)" type="checkbox" style="height:20px;width:20px;"/></td>
			       <td><img src="upload/${car.shp_tp }" alt="" style="height:50px;width:50px;"></td>
			       <td>
			          <a href="sale_search_goto_sku_detail/${car.sku_id }/${car.shp_id }.do">${car.sku_mch }</a>
			       </td>
			       <td>${car.sku_jg }</td>
			       <td>${car.hj }</td>
			       <td>
					  <img src="images/jian.jpg" id='jian' alt="" style="width:20px;" onClick="javascript:sale_shoppingCar_change_tjshl(${car.id},${car.sku_id},${car.tjshl-1});">
			          <input type="text" id="numbox" name="num" value="${car.tjshl }" style="height:22px;width:40px;text-align:center">
			          <img src="images/jia.jpg" id='jia' alt=""  style="width:20px;" onClick="javascript:sale_shoppingCar_change_tjshl(${car.id},${car.sku_id},${car.tjshl+1});">
			       </td>
                   <td>${car.kc_dz }</td>
			       <td><a href="delete_goods/${car.sku_id }.do">删除</a></td>
		        </tr>
		       </c:forEach>
		   </tbody>
	 	</table>
	</div>
	<div class="Cprice">
		<div class="price" >总价：${list_car_sum }</div>
		<a href="goto_check_order.do"><div class="jiesuan" >结算</div></a>
	</div>
</body>
</html>