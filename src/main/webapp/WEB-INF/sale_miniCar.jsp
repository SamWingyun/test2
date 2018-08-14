<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";%>
<base href="<%=basePath%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function sale_miniCar_show() {
		$.post("get_miniCar.do", function(data) {
			$("#sale_miniCar_div").html(data);
		});
		$("#sale_miniCar_div").show();
	}
	
	function sale_miniCar_hide() {

		$("#sale_miniCar_div").hide();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="card" >
		    <a href="goto_shoppingCar.do"  onmouseover="sale_miniCar_show()"
		        onmouseout="sale_miniCar_hide()">购物车<div class="num">
		        <c:forEach items="${sessionScope.session_car_list }" var="car" varStatus="index">
		        </c:forEach>
		        </div></a>
			<div class="cart_pro" id="sale_miniCar_div" style="display: none;style="font-family:微软雅黑;"">
				
			</div>
		</div>

</body>
</html>