<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" href="css/style.css">
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/"; %>
<base href="<%=basePath%>" />
<script type="text/javascript" src='js/jquery-1.7.2.min.js'></script>
<script type="text/javascript">
   
	function sale_shoppingCar_change_tjshl(car_id,sku_id,tjshl){
	
		   $.post("change_shoppingCar.do",{"car_id":car_id,"sku_id":sku_id,tjshl:tjshl,"shfxz":"1"},function(data){
				$("#sale_shoppingCar_inner").html(data);
			})
	}
	
	function sale_shoppingCar_change_item(car_id,sku_id,shfxz){
		if(shfxz==true){
			shfxz=1
		}else{
			shfxz=0
		}
		$.post("change_shoppingCar.do",{"car_id":car_id,"sku_id":sku_id,tjshl:0,"shfxz":shfxz},function(data){
			$("#sale_shoppingCar_inner").html(data);
		})
	}

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
				<input type="submit" class="clik" value="搜索" style="height:32px;width:70px">
			</div>
			
		</div>

       <!-- 引入mini购物车 -->
	   <jsp:include page="sale_miniCar.jsp"></jsp:include>
	
	</div>
    
    <!-- 动态引入购物车内嵌页 -->
	<div id="sale_shoppingCar_inner">
	   <jsp:include page="sale_shoppingCar_inner.jsp"></jsp:include>
    </div>
	
	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div> 
</body>
</html>